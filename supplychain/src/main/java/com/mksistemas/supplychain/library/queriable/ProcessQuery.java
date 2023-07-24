package com.mksistemas.supplychain.library.queriable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mksistemas.supplychain.library.LibraryMessages;
import com.mksistemas.supplychain.library.exceptions.BusinessException;

import jakarta.persistence.EntityManager;

public class ProcessQuery<TResult> {

	private final EntityManager entityManager;
	private Optional<String> query = Optional.empty();
	private Optional<Class<TResult>> resultClass = Optional.empty();
	private Optional<Consumer<NativeQuery<TResult>>> additionalConsumer = Optional.empty();
	private Optional<Pageable> pageable = Optional.empty();
	private Optional<Function<Object[], TResult>> transform = Optional.empty();

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
	
	public ProcessQuery<TResult> ofType(Class<TResult> resultClass) {
		Objects.requireNonNull(resultClass);
		this.resultClass = Optional.of(resultClass);
		return this;
	}

	public ProcessQuery<TResult> setAdditional(Consumer<NativeQuery<TResult>> additionalConsumer) {
		this.additionalConsumer = Optional.of(additionalConsumer);
		return this;
	}

	public ProcessQuery<TResult> setPageable(Pageable pageable) {
		this.pageable = Optional.of(pageable);
		return this;
	}

	public ProcessQuery<TResult> setTransform(Function<Object[], TResult> transform) {
		Objects.requireNonNull(transform);
		this.transform = Optional.of(transform);
		return this;
	}

	private TResult transformTuples(Object[] tuples, String[] aliases) {
		Map<String, Object> maps = new HashMap<>();
		for (int count = 0; count < aliases.length; count++)
			maps.put(aliases[count], tuples[count]);
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(maps, resultClass.get());
	}

	public Slice<TResult> run() {
		Objects.nonNull(resultClass);
		String queryData = query.orElseThrow(() -> new BusinessException(LibraryMessages.QUERY_NOT_INFORMED));
		return pageable.isEmpty() ? executeNonPageableQuery(queryData) : executePageableQuery(queryData);
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	public TResult runSingle() {
		Objects.nonNull(resultClass);
		String queryData = query.orElseThrow(() -> new BusinessException(LibraryMessages.QUERY_NOT_INFORMED));
		NativeQuery<TResult> nativeQuery = entityManager.unwrap(Session.class).createNativeQuery(queryData)
				.setTupleTransformer((tuples, aliases) -> transform.isPresent() ? transform.get().apply(tuples)
						: transformTuples(tuples, aliases));
		additionalConsumer.ifPresent(consumer -> consumer.accept(nativeQuery));
		return nativeQuery.getSingleResult();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private Slice<TResult> executeNonPageableQuery(String queryData) {
		NativeQuery<TResult> nativeQuery = entityManager.unwrap(Session.class).createNativeQuery(queryData)
				.setTupleTransformer((tuples, aliases) -> transform.isPresent() ? transform.get().apply(tuples)
						: transformTuples(tuples, aliases));
		additionalConsumer.ifPresent(consumer -> consumer.accept(nativeQuery));
		List<TResult> resultado = nativeQuery.getResultList();
		return new SliceImpl<>(resultado);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private Slice<TResult> executePageableQuery(String queryData) {
		Pageable pageableData = pageable.get();
		int maxItens = pageableData.getPageSize() + 1;
		NativeQuery<TResult> nativeQuery = entityManager.unwrap(Session.class).createNativeQuery(queryData)
				.setFirstResult(pageableData.getPageNumber() * maxItens).setMaxResults(maxItens)
				.setTupleTransformer((tuples, aliases) -> transform.isPresent() ? transform.get().apply(tuples)
						: transformTuples(tuples, aliases));
		additionalConsumer.ifPresent(consumer -> consumer.accept(nativeQuery));
		List<TResult> resultado = nativeQuery.getResultList();
		boolean hasNext = resultado.size() == maxItens;
		if (hasNext)
			resultado = resultado.subList(0, maxItens - 1);
		return new SliceImpl<>(resultado, pageableData, hasNext);
	}

}

