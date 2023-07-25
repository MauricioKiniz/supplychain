package com.mksistemas.supplychain.grupoeconomico.adapter.db;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.mksistemas.supplychain.grupoeconomico.application.alterar.AlterarGrupoEconomicoRepository;
import com.mksistemas.supplychain.grupoeconomico.application.ativar.AtivarGrupoEconomicoRepository;
import com.mksistemas.supplychain.grupoeconomico.application.criar.CriarGrupoEconomicoRepository;
import com.mksistemas.supplychain.grupoeconomico.application.desativar.DesativarGrupoEconomicoRepository;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomico;
import com.mksistemas.supplychain.grupoeconomico.domain.GrupoEconomicoId;

@Component
class GrupoEconomicoJpaRepository implements CriarGrupoEconomicoRepository, AlterarGrupoEconomicoRepository,
		AtivarGrupoEconomicoRepository,
		DesativarGrupoEconomicoRepository {

	private final BaseGrupoEconomicoJpaRepository baseRepository;

	public GrupoEconomicoJpaRepository(BaseGrupoEconomicoJpaRepository baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	public void salvar(GrupoEconomico grupo) {
		baseRepository.save(grupo);
	}

	@Override
	public Optional<GrupoEconomico> buscarPorId(GrupoEconomicoId grupoEconomicoId) {
		return baseRepository.findById(grupoEconomicoId);
	}

}
