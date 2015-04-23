/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gsan.gerencial.faturamento;

import gsan.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaPerfil;import gsan.gerencial.atendimentopublico.ligacaoagua.GLigacaoAguaSituacao;import gsan.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoPerfil;import gsan.gerencial.atendimentopublico.ligacaoesgoto.GLigacaoEsgotoSituacao;import gsan.gerencial.cadastro.cliente.GClienteTipo;import gsan.gerencial.cadastro.cliente.GEsferaPoder;import gsan.gerencial.cadastro.imovel.GCategoria;import gsan.gerencial.cadastro.imovel.GImovelPerfil;import gsan.gerencial.cadastro.imovel.GSubcategoria;import gsan.gerencial.cadastro.localidade.GGerenciaRegional;import gsan.gerencial.cadastro.localidade.GLocalidade;import gsan.gerencial.cadastro.localidade.GQuadra;import gsan.gerencial.cadastro.localidade.GSetorComercial;import gsan.gerencial.cadastro.localidade.GUnidadeNegocio;import gsan.gerencial.cobranca.GDocumentoTipo;import gsan.gerencial.faturamento.credito.GCreditoOrigem;import gsan.gerencial.faturamento.credito.GCreditoTipo;import gsan.gerencial.faturamento.debito.GDebitoTipo;import gsan.gerencial.financeiro.GFinanciamentoTipo;import gsan.gerencial.financeiro.lancamento.GLancamentoItemContabil;import gsan.gerencial.micromedicao.GRota;import java.io.Serializable;import java.math.BigDecimal;import java.util.Date;import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoRefaturamento implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private int codigoSetorcomercial;

    /** persistent field */
    private int numeroQuadra;

    /** persistent field */
    private int quantidadeContasRetificadas;

    /** persistent field */
    private BigDecimal valorCanceladoAgua;

    /** persistent field */
    private int quantidadeContasCanceladas;

    /** persistent field */
    private int volumeCanceladoAgua;

    /** persistent field */
    private int quantidadeContasIncluidas;

    /** persistent field */
    private BigDecimal valorCanceladoEsgoto;

    /** persistent field */
    private int volumeCanceladoEsgoto;

    /** persistent field */
    private BigDecimal valorIncluidoAgua;

    /** persistent field */
    private int volumeIncluidoAgua;

    /** persistent field */
    private BigDecimal valorIncluidoEsgoto;

    /** persistent field */
    private int volumeIncluidoEsgoto;


    private BigDecimal valorIncluidoImpostos;
    
    /** persistent field */
    private BigDecimal valorCanceladoImpostos;
    
    /** persistent field */
    private BigDecimal valorIncluidoGuias;

    /** persistent field */
    private BigDecimal valorCanceladoGuias;

    /** persistent field */
    private Integer quantidadeGuiasIncluidas;
    
    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private GGerenciaRegional gerGerenciaRegional;

    /** persistent field */
    private GSubcategoria gerSubcategoria;

    /** persistent field */
    private GSetorComercial gerSetorComercial;

    /** persistent field */
    private GLigacaoAguaPerfil gerLigacaoAguaPerfil;

    /** persistent field */
    private GClienteTipo gerClienteTipo;

    /** persistent field */
    private GEsferaPoder gerEsferaPoder;

    /** persistent field */
    private GUnidadeNegocio gerUnidadeNegocio;

    /** persistent field */
    private GLigacaoAguaSituacao gerLigacaoAguaSituacao;

    /** persistent field */
    private GLocalidade gerLocalidade;

    /** persistent field */
    private GLocalidade gerLocalidadeElo;

    /** persistent field */
    private GCategoria gerCategoria;

    /** persistent field */
    private GImovelPerfil gerImovelPerfil;

    /** persistent field */
    private GQuadra gerQuadra;

    /** persistent field */
    private GRota gerRota;

    /** persistent field */
    private GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao;

    /** persistent field */
    private GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil;

    /** persistent field */
    private BigDecimal valorIncluidoCredito;
    
    /** persistent field */
    private Integer quantidadeDocumentosFaturadosCredito;
    
    /** persistent field */
    private BigDecimal valorIncluidoOutros;
    
    /** persistent field */
    private Integer quantidadeGuiasCanceladas;
    
    /** persistent field */
    private GLancamentoItemContabil gerLancamentoItemContabil;
    
    /** persistent field */
    private GFinanciamentoTipo gerFinanciamentoTipo;
    
    /** persistent field */
    private GCreditoOrigem gerCreditoOrigem;
    
    /** persistent field */
    private GDocumentoTipo gerDocumentoTipo;
    
    /** persistent field */
    private BigDecimal valorCanceladoCredito;
    
    /** persistent field */
    private BigDecimal valorCanceladoOutros;

    /** persistent field */
    private int anoMesReferenciaConta;
    
    /** persistent field */
    private GDebitoTipo gerDebitoTipo;
    
    /** persistent field */
    private GCreditoTipo gerCreditoTipo;
    
	public UnResumoRefaturamento(int anoMesReferencia, int anoMesReferenciaConta,
			GGerenciaRegional gGerenciaRegional, GUnidadeNegocio gUnidadeNegocio,
			GLocalidade gLocalidade, GSetorComercial gSetorComercial, GQuadra gQuadra, 
			int codigoSetorcomercial, int numeroQuadra, GImovelPerfil gImovelPerfil,
			GEsferaPoder gEsferaPoder, GClienteTipo gClienteTipo, GLigacaoAguaSituacao gLigacaoAguaSituacao,
			GLigacaoEsgotoSituacao gLigacaoEsgotoSituacao, GCategoria gCategoria, GSubcategoria gSubcategoria,
			GLigacaoAguaPerfil gLigacaoAguaPerfil, GLigacaoEsgotoPerfil gLigacaoEsgotoPerfil,
			int quantidadeContasRetificadas, int quantidadeContasCanceladas, BigDecimal valorCanceladoAgua, 
			BigDecimal valorCanceladoEsgoto, int volumeCanceladoAgua, int volumeCanceladoEsgoto, 
			int quantidadeContasIncluidas, BigDecimal valorIncluidoAgua, BigDecimal valorIncluidoEsgoto, 
			int volumeIncluidoAgua, int volumeIncluidoEsgoto,Date ultimaAlteracao,
			GLocalidade gerLocalidadeElo, GRota gerRota, BigDecimal valorIncluidoImpostos, 
			BigDecimal valorCanceladoImpostos,BigDecimal valorIncluidoGuias, BigDecimal valorCanceladoGuias, 
			int quantidadeGuiasIncluidas, BigDecimal valorIncluidoCredito,BigDecimal valorIncluidoOutros, 
			int quantidadeGuiasCanceladas, BigDecimal valorCanceladoCredito, BigDecimal valorCanceladoOutros){
		this.anoMesReferencia = anoMesReferencia;
		this.anoMesReferenciaConta = anoMesReferenciaConta;
		this.gerGerenciaRegional = gGerenciaRegional;
		this.gerUnidadeNegocio = gUnidadeNegocio;
		this.gerLocalidade = gLocalidade;
		this.gerSetorComercial = gSetorComercial;
		this.gerQuadra = gQuadra;
		this.codigoSetorcomercial = codigoSetorcomercial;
		this.numeroQuadra = numeroQuadra;
		this.gerImovelPerfil = gImovelPerfil;
		this.gerEsferaPoder = gEsferaPoder;
		this.gerClienteTipo = gClienteTipo;
		this.gerLigacaoAguaSituacao = gLigacaoAguaSituacao;
		this.gerLigacaoEsgotoSituacao = gLigacaoEsgotoSituacao;
		this.gerCategoria = gCategoria;
		this.gerSubcategoria = gSubcategoria;
		this.gerLigacaoAguaPerfil = gLigacaoAguaPerfil;
		this.gerLigacaoEsgotoPerfil = gLigacaoEsgotoPerfil;
	    this.quantidadeContasRetificadas = quantidadeContasRetificadas;
	    this.quantidadeContasCanceladas = quantidadeContasCanceladas;
	    this.valorCanceladoAgua = valorCanceladoAgua;
	    this.valorCanceladoEsgoto = valorCanceladoEsgoto;
	    this.volumeCanceladoAgua = volumeCanceladoAgua;
	    this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
	    this.quantidadeContasIncluidas = quantidadeContasIncluidas;
	    this.valorIncluidoAgua = valorIncluidoAgua;
	    this.valorIncluidoEsgoto = valorIncluidoEsgoto;
	    this.volumeIncluidoAgua = volumeIncluidoAgua;
	    this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
		this.ultimaAlteracao = ultimaAlteracao;
		this.gerLocalidadeElo = gerLocalidadeElo;
		this.gerRota = gerRota;
	    this.valorIncluidoImpostos = valorIncluidoImpostos;
	    this.valorCanceladoImpostos = valorCanceladoImpostos;
        this.valorIncluidoGuias = valorIncluidoGuias;
        this.valorCanceladoGuias = valorCanceladoGuias;
	    this.quantidadeGuiasIncluidas = quantidadeGuiasIncluidas;
	    this.valorIncluidoCredito = valorIncluidoCredito;
	    this.valorIncluidoOutros = valorIncluidoOutros;
	    this.quantidadeGuiasCanceladas = quantidadeGuiasCanceladas;
	    this.valorCanceladoCredito = valorCanceladoCredito;
	    this.valorCanceladoOutros = valorCanceladoOutros;
	    
	}

    /** full constructor */
    public UnResumoRefaturamento(
    		Integer id, 
    		int anoMesReferencia, 
    		int anoMesReferenciaConta, 
    		int codigoSetorcomercial, 
    		int numeroQuadra, 
    		int quantidadeContasRetificadas, 
    		BigDecimal valorCanceladoAgua, 
    		int quantidadeContasCanceladas, 
    		int volumeCanceladoAgua, 
    		int quantidadeContasIncluidas, 
    		BigDecimal valorCanceladoEsgoto, 
    		int volumeCanceladoEsgoto, 
    		BigDecimal valorIncluidoAgua, 
    		int volumeIncluidoAgua, 
    		BigDecimal valorIncluidoEsgoto, 
    		int volumeIncluidoEsgoto, 
    		Date ultimaAlteracao, 
    	    BigDecimal valorIncluidoImpostos,
    	    BigDecimal valorCanceladoImpostos,
    	    BigDecimal valorIncluidoGuias,
    	    BigDecimal valorCanceladoGuias,
    	    int quantidadeGuiasIncluidas,
    		GGerenciaRegional gerGerenciaRegional, 
    		GSubcategoria gerSubcategoria, 
    		GSetorComercial gerSetorComercial, 
    		GLigacaoAguaPerfil gerLigacaoAguaPerfil, 
    		GClienteTipo gerClienteTipo, 
    		GEsferaPoder gerEsferaPoder, 
    		GUnidadeNegocio gerUnidadeNegocio, 
    		GLigacaoAguaSituacao gerLigacaoAguaSituacao, 
    		GLocalidade gerLocalidade, 
    		GLocalidade gerLocalidadeElo, 
    		GCategoria gerCategoria, 
    		GImovelPerfil gerImovelPerfil, 
    		GQuadra gerQuadra, 
    		GRota gerRota, 
    		GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao, 
    		GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.id = id;
        this.anoMesReferencia = anoMesReferencia;
        this.anoMesReferenciaConta = anoMesReferenciaConta;
        this.codigoSetorcomercial = codigoSetorcomercial;
        this.numeroQuadra = numeroQuadra;
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
        this.valorCanceladoAgua = valorCanceladoAgua;
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
        this.volumeCanceladoAgua = volumeCanceladoAgua;
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
        this.valorIncluidoAgua = valorIncluidoAgua;
        this.volumeIncluidoAgua = volumeIncluidoAgua;
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorIncluidoImpostos = valorIncluidoImpostos;
        this.valorCanceladoImpostos = valorCanceladoImpostos;
        this.valorIncluidoGuias = valorIncluidoGuias;
        this.valorCanceladoGuias = valorCanceladoGuias;
        this.quantidadeGuiasIncluidas = quantidadeGuiasIncluidas;
        this.gerGerenciaRegional = gerGerenciaRegional;
        this.gerSubcategoria = gerSubcategoria;
        this.gerSetorComercial = gerSetorComercial;
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
        this.gerClienteTipo = gerClienteTipo;
        this.gerEsferaPoder = gerEsferaPoder;
        this.gerUnidadeNegocio = gerUnidadeNegocio;
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
        this.gerLocalidade = gerLocalidade;
        this.gerLocalidadeElo = gerLocalidadeElo;
        this.gerCategoria = gerCategoria;
        this.gerImovelPerfil = gerImovelPerfil;
        this.gerQuadra = gerQuadra;
        this.gerRota = gerRota;
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    /** default constructor */
    public UnResumoRefaturamento() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public int getCodigoSetorcomercial() {
        return this.codigoSetorcomercial;
    }

    public void setCodigoSetorcomercial(int codigoSetorcomercial) {
        this.codigoSetorcomercial = codigoSetorcomercial;
    }

    public int getNumeroQuadra() {
        return this.numeroQuadra;
    }

    public void setNumeroQuadra(int numeroQuadra) {
        this.numeroQuadra = numeroQuadra;
    }

    public int getQuantidadeContasRetificadas() {
        return this.quantidadeContasRetificadas;
    }

    public void setQuantidadeContasRetificadas(int quantidadeContasRetificadas) {
        this.quantidadeContasRetificadas = quantidadeContasRetificadas;
    }

    public BigDecimal getValorCanceladoAgua() {
        return this.valorCanceladoAgua;
    }

    public void setValorCanceladoAgua(BigDecimal valorCanceladoAgua) {
        this.valorCanceladoAgua = valorCanceladoAgua;
    }

    public int getQuantidadeContasCanceladas() {
        return this.quantidadeContasCanceladas;
    }

    public void setQuantidadeContasCanceladas(int quantidadeContasCanceladas) {
        this.quantidadeContasCanceladas = quantidadeContasCanceladas;
    }

    public int getVolumeCanceladoAgua() {
        return this.volumeCanceladoAgua;
    }

    public void setVolumeCanceladoAgua(int volumeCanceladoAgua) {
        this.volumeCanceladoAgua = volumeCanceladoAgua;
    }

    public int getQuantidadeContasIncluidas() {
        return this.quantidadeContasIncluidas;
    }

    public void setQuantidadeContasIncluidas(int quantidadeContasIncluidas) {
        this.quantidadeContasIncluidas = quantidadeContasIncluidas;
    }

    public BigDecimal getValorCanceladoEsgoto() {
        return this.valorCanceladoEsgoto;
    }

    public void setValorCanceladoEsgoto(BigDecimal valorCanceladoEsgoto) {
        this.valorCanceladoEsgoto = valorCanceladoEsgoto;
    }

    public int getVolumeCanceladoEsgoto() {
        return this.volumeCanceladoEsgoto;
    }

    public void setVolumeCanceladoEsgoto(int volumeCanceladoEsgoto) {
        this.volumeCanceladoEsgoto = volumeCanceladoEsgoto;
    }

    public BigDecimal getValorIncluidoAgua() {
        return this.valorIncluidoAgua;
    }

    public void setValorIncluidoAgua(BigDecimal valorIncluidoAgua) {
        this.valorIncluidoAgua = valorIncluidoAgua;
    }

    public int getVolumeIncluidoAgua() {
        return this.volumeIncluidoAgua;
    }

    public void setVolumeIncluidoAgua(int volumeIncluidoAgua) {
        this.volumeIncluidoAgua = volumeIncluidoAgua;
    }

    public BigDecimal getValorIncluidoEsgoto() {
        return this.valorIncluidoEsgoto;
    }

    public void setValorIncluidoEsgoto(BigDecimal valorIncluidoEsgoto) {
        this.valorIncluidoEsgoto = valorIncluidoEsgoto;
    }

    public int getVolumeIncluidoEsgoto() {
        return this.volumeIncluidoEsgoto;
    }

    public void setVolumeIncluidoEsgoto(int volumeIncluidoEsgoto) {
        this.volumeIncluidoEsgoto = volumeIncluidoEsgoto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GGerenciaRegional getGerGerenciaRegional() {
        return this.gerGerenciaRegional;
    }

    public void setGerGerenciaRegional(GGerenciaRegional gerGerenciaRegional) {
        this.gerGerenciaRegional = gerGerenciaRegional;
    }

    public GSubcategoria getGerSubcategoria() {
        return this.gerSubcategoria;
    }

    public void setGerSubcategoria(GSubcategoria gerSubcategoria) {
        this.gerSubcategoria = gerSubcategoria;
    }

    public GSetorComercial getGerSetorComercial() {
        return this.gerSetorComercial;
    }

    public void setGerSetorComercial(GSetorComercial gerSetorComercial) {
        this.gerSetorComercial = gerSetorComercial;
    }

    public GLigacaoAguaPerfil getGerLigacaoAguaPerfil() {
        return this.gerLigacaoAguaPerfil;
    }

    public void setGerLigacaoAguaPerfil(GLigacaoAguaPerfil gerLigacaoAguaPerfil) {
        this.gerLigacaoAguaPerfil = gerLigacaoAguaPerfil;
    }

    public GClienteTipo getGerClienteTipo() {
        return this.gerClienteTipo;
    }

    public void setGerClienteTipo(GClienteTipo gerClienteTipo) {
        this.gerClienteTipo = gerClienteTipo;
    }

    public GEsferaPoder getGerEsferaPoder() {
        return this.gerEsferaPoder;
    }

    public void setGerEsferaPoder(GEsferaPoder gerEsferaPoder) {
        this.gerEsferaPoder = gerEsferaPoder;
    }

    public GUnidadeNegocio getGerUnidadeNegocio() {
        return this.gerUnidadeNegocio;
    }

    public void setGerUnidadeNegocio(GUnidadeNegocio gerUnidadeNegocio) {
        this.gerUnidadeNegocio = gerUnidadeNegocio;
    }

    public GLigacaoAguaSituacao getGerLigacaoAguaSituacao() {
        return this.gerLigacaoAguaSituacao;
    }

    public void setGerLigacaoAguaSituacao(GLigacaoAguaSituacao gerLigacaoAguaSituacao) {
        this.gerLigacaoAguaSituacao = gerLigacaoAguaSituacao;
    }

    public GLocalidade getGerLocalidade() {
        return this.gerLocalidade;
    }

    public void setGerLocalidade(GLocalidade gerLocalidade) {
        this.gerLocalidade = gerLocalidade;
    }

    public GLocalidade getGerLocalidadeElo() {
        return this.gerLocalidadeElo;
    }

    public void setGerLocalidadeElo(GLocalidade gerLocalidadeElo) {
        this.gerLocalidadeElo = gerLocalidadeElo;
    }

    public GCategoria getGerCategoria() {
        return this.gerCategoria;
    }

    public void setGerCategoria(GCategoria gerCategoria) {
        this.gerCategoria = gerCategoria;
    }

    public GImovelPerfil getGerImovelPerfil() {
        return this.gerImovelPerfil;
    }

    public void setGerImovelPerfil(GImovelPerfil gerImovelPerfil) {
        this.gerImovelPerfil = gerImovelPerfil;
    }

    public GQuadra getGerQuadra() {
        return this.gerQuadra;
    }

    public void setGerQuadra(GQuadra gerQuadra) {
        this.gerQuadra = gerQuadra;
    }

    public GRota getGerRota() {
        return this.gerRota;
    }

    public void setGerRota(GRota gerRota) {
        this.gerRota = gerRota;
    }

    public GLigacaoEsgotoSituacao getGerLigacaoEsgotoSituacao() {
        return this.gerLigacaoEsgotoSituacao;
    }

    public void setGerLigacaoEsgotoSituacao(GLigacaoEsgotoSituacao gerLigacaoEsgotoSituacao) {
        this.gerLigacaoEsgotoSituacao = gerLigacaoEsgotoSituacao;
    }

    public GLigacaoEsgotoPerfil getGerLigacaoEsgotoPerfil() {
        return this.gerLigacaoEsgotoPerfil;
    }

    public void setGerLigacaoEsgotoPerfil(GLigacaoEsgotoPerfil gerLigacaoEsgotoPerfil) {
        this.gerLigacaoEsgotoPerfil = gerLigacaoEsgotoPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public GCreditoOrigem getGerCreditoOrigem() {
		return gerCreditoOrigem;
	}

	public void setGerCreditoOrigem(GCreditoOrigem gerCreditoOrigem) {
		this.gerCreditoOrigem = gerCreditoOrigem;
	}


	public Integer getQuantidadeDocumentosFaturadosCredito() {
		return quantidadeDocumentosFaturadosCredito;
	}

	public void setQuantidadeDocumentosFaturadosCredito(
			Integer quantidadeDocumentosFaturadosCredito) {
		this.quantidadeDocumentosFaturadosCredito = quantidadeDocumentosFaturadosCredito;
	}

	public int getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	public void setAnoMesReferenciaConta(int anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public GCreditoTipo getGerCreditoTipo() {
		return gerCreditoTipo;
	}

	public void setGerCreditoTipo(GCreditoTipo gerCreditoTipo) {
		this.gerCreditoTipo = gerCreditoTipo;
	}

	public GDebitoTipo getGerDebitoTipo() {
		return gerDebitoTipo;
	}

	public void setGerDebitoTipo(GDebitoTipo gerDebitoTipo) {
		this.gerDebitoTipo = gerDebitoTipo;
	}

	public GDocumentoTipo getGerDocumentoTipo() {
		return gerDocumentoTipo;
	}

	public void setGerDocumentoTipo(GDocumentoTipo gerDocumentoTipo) {
		this.gerDocumentoTipo = gerDocumentoTipo;
	}

	public GFinanciamentoTipo getGerFinanciamentoTipo() {
		return gerFinanciamentoTipo;
	}

	public void setGerFinanciamentoTipo(GFinanciamentoTipo gerFinanciamentoTipo) {
		this.gerFinanciamentoTipo = gerFinanciamentoTipo;
	}

	public GLancamentoItemContabil getGerLancamentoItemContabil() {
		return gerLancamentoItemContabil;
	}

	public void setGerLancamentoItemContabil(
			GLancamentoItemContabil gerLancamentoItemContabil) {
		this.gerLancamentoItemContabil = gerLancamentoItemContabil;
	}

	public Integer getQuantidadeGuiasCanceladas() {
		return quantidadeGuiasCanceladas;
	}

	public void setQuantidadeGuiasCanceladas(Integer quantidadeGuiasCanceladas) {
		this.quantidadeGuiasCanceladas = quantidadeGuiasCanceladas;
	}

	public BigDecimal getValorCanceladoCredito() {
		return valorCanceladoCredito;
	}

	public void setValorCanceladoCredito(BigDecimal valorCanceladoCredito) {
		this.valorCanceladoCredito = valorCanceladoCredito;
	}

	public BigDecimal getValorCanceladoOutros() {
		return valorCanceladoOutros;
	}

	public void setValorCanceladoOutros(BigDecimal valorCanceladoOutros) {
		this.valorCanceladoOutros = valorCanceladoOutros;
	}

	public BigDecimal getValorIncluidoCredito() {
		return valorIncluidoCredito;
	}

	public void setValorIncluidoCredito(BigDecimal valorIncluidoCredito) {
		this.valorIncluidoCredito = valorIncluidoCredito;
	}

	public BigDecimal getValorIncluidoOutros() {
		return valorIncluidoOutros;
	}

	public void setValorIncluidoOutros(BigDecimal valorIncluidoOutros) {
		this.valorIncluidoOutros = valorIncluidoOutros;
	}


	public BigDecimal getValorCanceladoGuias() {
		return valorCanceladoGuias;
	}

	public void setValorCanceladoGuias(BigDecimal valorCanceladoGuias) {
		this.valorCanceladoGuias = valorCanceladoGuias;
	}

	public BigDecimal getValorCanceladoImpostos() {
		return valorCanceladoImpostos;
	}

	public void setValorCanceladoImpostos(BigDecimal valorCanceladoImpostos) {
		this.valorCanceladoImpostos = valorCanceladoImpostos;
	}

	public BigDecimal getValorIncluidoGuias() {
		return valorIncluidoGuias;
	}

	public void setValorIncluidoGuias(BigDecimal valorIncluidoGuias) {
		this.valorIncluidoGuias = valorIncluidoGuias;
	}

	public BigDecimal getValorIncluidoImpostos() {
		return valorIncluidoImpostos;
	}

	public void setValorIncluidoImpostos(BigDecimal valorIncluidoImpostos) {
		this.valorIncluidoImpostos = valorIncluidoImpostos;
	}

	public Integer getQuantidadeGuiasIncluidas() {
		return quantidadeGuiasIncluidas;
	}

	public void setQuantidadeGuiasIncluidas(Integer quantidadeGuiasIncluidas) {
		this.quantidadeGuiasIncluidas = quantidadeGuiasIncluidas;
	}



	
}
