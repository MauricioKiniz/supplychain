package com.mksistemas.supplychain.library.queryable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.logging.log4j.util.Strings;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mksistemas.supplychain.library.exceptions.BusinessException;

import jakarta.persistence.EntityManager;

public class ProcessQuery<TResult> {

	private final EntityManager entityManager;

	public static final String QUERY_NOT_INFORMED = "{queriable.query.notinformed}";
	public static final String COUNT_QUERY_NOT_INFORMED = "{queriable.countquery.notinformed}";
	public static final String QUERY_RESULTCLASS_NOT_INFORMED = "{queriable.resultclass.notinformed}";
	public static final String INVALID_EXECUTION_CALL = "{queriable.singleresult.called.afterresultset}";

	private Optional<String> query = Optional.empty();
	private Optional<Class<TResult>> resultClass = Optional.empty();
	private Optional<Consumer<NativeQuery<TResult>>> additionalConsumer = Optional.empty();
	private Optional<Consumer<NativeQuery<Long>>> additionalCountConsumer;
	private Optional<Pageable> pageable = Optional.empty();
	private Optional<Function<Object[], TResult>> transform = Optional.empty();
	private Optional<String> countQuery = Optional.empty();
	private Optional<List<TResult>> resultList = Optional.empty();
	private int maxItens;
	private boolean hasNext;
	private Long totalRegisters;

	private ProcessQuery(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public static <TResult> ProcessQuery<TResult> of(EntityManager entityManager) {
		return new ProcessQuery<>(entityManager);
	}

	public ProcessQuery<TResult> createQuery(String query) {
		Objects.requireNonNull(query);
		this.query = Optional.of(query);
		return this;
	}
	
	public ProcessQuery<TResult> createCountQuery(String countQuery) {
		Objects.requireNonNull(countQuery);
		this.countQuery = Optional.of(countQuery);
		return this;
	}

	public ProcessQuery<TResult> ofType(Class<TResult> resultClass) {
		Objects.requireNonNull(resultClass);
		this.resultClass = Optional.of(resultClass);
		return this;
	}

	public ProcessQuery<TResult> setAdditional(Consumer<NativeQuery<TResult>> additionalConsumer) {
		this.additionalConsumer = Optional.of(additionalConsumer);
		return this;
	}

	public ProcessQuery<TResult> setAdditionalCount(Consumer<NativeQuery<Long>> additionalCountConsumer) {
		this.additionalCountConsumer = Optional.of(additionalCountConsumer);
		return this;
	}

	public ProcessQuery<TResult> setPageable(Pageable pageable) {
		this.pageable = Optional.of(pageable);
		clear();
		return this;
	}

	public ProcessQuery<TResult> setTransform(Function<Object[], TResult> transform) {
		Objects.requireNonNull(transform);
		this.transform = Optional.of(transform);
		return this;
	}

	public ProcessQuery<TResult> withoutTransform() {
		@SuppressWarnings("unchecked")
		final Function<Object[], TResult> defaultTransform = (tuples) -> (TResult) tuples;
		this.transform = Optional.of(defaultTransform);
		return this;
	}

	private TResult transformTuples(Object[] tuples, String[] aliases) {
		Map<String, Object> maps = new HashMap<>();
		for (int count = 0; count < aliases.length; count++)
			maps.put(aliases[count], tuples[count]);
		final ObjectMapper mapper = JsonMapper.builder()
				.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
				.configure(JsonReadFeature.ALLOW_MISSING_VALUES, true).build();
		return mapper.convertValue(maps, resultClass.get());
	}

	private void executeQueryToList() {
		if (resultList.isEmpty()) {
			executeMainQueryToList();
			executeCountQuery();
		}
	}

	public void clear() {
		this.resultList.ifPresent(Collection::clear);
		this.resultList = Optional.empty();
		this.maxItens = 0;
		this.totalRegisters = 0l;
		this.hasNext = false;
	}

	private void executeCountQuery() {
		if (countQuery.isPresent()) {
			String countQueryData = countQuery.get();
			if (Strings.isBlank(countQueryData))
				throw new IllegalArgumentException(COUNT_QUERY_NOT_INFORMED);
			NativeQuery<Long> nativeTotalQuery = entityManager.unwrap(Session.class).createNativeQuery(countQueryData,
					Long.class);
			additionalCountConsumer.ifPresent(consumer -> consumer.accept(nativeTotalQuery));
			this.totalRegisters = nativeTotalQuery.getSingleResultOrNull();
		}
	}

	private void executeMainQueryToList() {
		if (query.isEmpty() || Strings.isBlank(query.get()))
			throw new IllegalArgumentException(QUERY_NOT_INFORMED);
		final NativeQuery<TResult> nativeQuery = entityManager.unwrap(Session.class)
				.createNativeQuery(query.get(), Object[].class)
				.setTupleTransformer((tuples, aliases) -> transform.isPresent() ? transform.get().apply(tuples)
						: transformTuples(tuples, aliases));
		additionalConsumer.ifPresent(consumer -> consumer.accept(nativeQuery));
		pageable.ifPresentOrElse(pageableData -> {
			maxItens = pageableData.getPageSize();
			nativeQuery.setMaxResults(maxItens).setFirstResult(pageableData.getPageNumber() * maxItens);
			List<TResult> nativeList = nativeQuery.getResultList();
			this.hasNext = nativeList.size() == maxItens;
			if (hasNext)
				nativeList = nativeList.subList(0, maxItens - 1);
			this.resultList = Optional.of(nativeList);
		}, () -> this.resultList = Optional.of(nativeQuery.getResultList()));
	}

	public Optional<TResult> runSingle() {
		Objects.nonNull(resultClass);
		if (this.resultList.isPresent())
			throw new IllegalCallerException(INVALID_EXECUTION_CALL);
		String queryData = query.orElseThrow(() -> new BusinessException(QUERY_NOT_INFORMED));
		NativeQuery<TResult> nativeQuery = entityManager.unwrap(Session.class)
				.createNativeQuery(queryData, Object[].class)
				.setTupleTransformer((tuples, aliases) -> transform.isPresent() ? transform.get().apply(tuples)
						: transformTuples(tuples, aliases));
		additionalConsumer.ifPresent(consumer -> consumer.accept(nativeQuery));
		TResult result = nativeQuery.getSingleResultOrNull();
		return result == null ? Optional.empty() : Optional.of(result);
	}

	public Slice<TResult> runAsSlice() {
		Objects.nonNull(resultClass);
		executeQueryToList();
		return pageable.isEmpty() ? new SliceImpl<>(this.resultList.get())
				: new SliceImpl<>(this.resultList.get(), pageable.get(), hasNext);
	}

	public Page<TResult> runAsPage() {
		Objects.nonNull(resultClass);
		executeQueryToList();
		return pageable.isEmpty() ? new PageImpl<>(this.resultList.get(), Pageable.unpaged(), totalRegisters)
				: new PageImpl<>(this.resultList.get(), pageable.get(), totalRegisters);
	}

	public List<TResult> runAsList() {
		Objects.nonNull(resultClass);
		executeQueryToList();
		return this.resultList.get();
	}

	public Stream<TResult> runAsStream() {
		Objects.nonNull(resultClass);
		executeQueryToList();
		return this.resultList.get().stream();
	}
}
