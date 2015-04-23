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
package gsan.gui.atendimentopublico.ligacaoagua;

import gsan.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0786]Filtrar Situacao de Ligacao de Agua 
 * 
 * @author Vinicius Medeiros
 * @date 15/05/2008
 */

public class FiltrarLigacaoAguaSituacaoActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String id;
	private String descricao;
	private String descricaoAbreviada;
	private String consumoMinimoFaturamento;
	private String indicadorFaturamentoSituacao = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaRede = ConstantesSistema.TODOS.toString();
	private String indicadorExistenciaLigacao = ConstantesSistema.TODOS.toString();
	private String indicadorUso = ConstantesSistema.TODOS.toString();
	private String tipoPesquisa = ConstantesSistema.TIPO_PESQUISA_INICIAL.toString();
	private String indicadorAbastecimento = ConstantesSistema.TODOS.toString();
	private String indicadorAtualizar = ConstantesSistema.TODOS.toString();
	private String indicadorAguaAtiva = ConstantesSistema.TODOS.toString();
	private String indicadorAguaDesligada = ConstantesSistema.TODOS.toString();
	private String indicadorAguaCadastrada = ConstantesSistema.TODOS.toString();
	private String indicadorAnalizeAgua = ConstantesSistema.TODOS.toString();
	private String indicadorPermissaoCorteEsgoto = ConstantesSistema.TODOS.toString();

	
	public String getIndicadorPermissaoCorteEsgoto() {
		return indicadorPermissaoCorteEsgoto;
	}

	public void setIndicadorPermissaoCorteEsgoto(String indicadorPermissaoCorteEsgoto) {
		this.indicadorPermissaoCorteEsgoto = indicadorPermissaoCorteEsgoto;
	}

	public String getConsumoMinimoFaturamento() {
		return consumoMinimoFaturamento;
	}

	public void setConsumoMinimoFaturamento(String consumoMinimoFaturamento) {
		this.consumoMinimoFaturamento = consumoMinimoFaturamento;
	}

	public String getIndicadorExistenciaLigacao() {
		return indicadorExistenciaLigacao;
	}

	public void setIndicadorExistenciaLigacao(String indicadorExistenciaLigacao) {
		this.indicadorExistenciaLigacao = indicadorExistenciaLigacao;
	}

	public String getIndicadorExistenciaRede() {
		return indicadorExistenciaRede;
	}

	public void setIndicadorExistenciaRede(String indicadorExistenciaRede) {
		this.indicadorExistenciaRede = indicadorExistenciaRede;
	}

	public String getIndicadorFaturamentoSituacao() {
		return indicadorFaturamentoSituacao;
	}

	public void setIndicadorFaturamentoSituacao(String indicadorFaturamentoSituacao) {
		this.indicadorFaturamentoSituacao = indicadorFaturamentoSituacao;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getIndicadorUso() {
		return indicadorUso;
	}

	public String getIndicadorAguaAtiva() {
		return indicadorAguaAtiva;
	}

	public void setIndicadorAguaAtiva(String indicadorAguaAtiva) {
		this.indicadorAguaAtiva = indicadorAguaAtiva;
	}

	public String getIndicadorAguaCadastrada() {
		return indicadorAguaCadastrada;
	}

	public void setIndicadorAguaCadastrada(String indicadorAguaCadastrada) {
		this.indicadorAguaCadastrada = indicadorAguaCadastrada;
	}

	public String getIndicadorAguaDesligada() {
		return indicadorAguaDesligada;
	}

	public void setIndicadorAguaDesligada(String indicadorAguaDesligada) {
		this.indicadorAguaDesligada = indicadorAguaDesligada;
	}

	public String getIndicadorAnalizeAgua() {
		return indicadorAnalizeAgua;
	}

	public void setIndicadorAnalizeAgua(String indicadorAnalizeAgua) {
		this.indicadorAnalizeAgua = indicadorAnalizeAgua;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getIndicadorAbastecimento() {
		return indicadorAbastecimento;
	}

	public void setIndicadorAbastecimento(String indicadorAbastecimento) {
		this.indicadorAbastecimento = indicadorAbastecimento;
	}
	
	

}
