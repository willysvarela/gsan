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
package gsan.atendimentopublico.registroatendimento.bean;

import gsan.cadastro.unidade.UnidadeOrganizacional;

import java.util.Date;

/**
 * Esta classe tem por finalidade facilitar a forma como ser� retornado o resultado do 
 * [SB0003] - Define Data Prevista e Unidade Destino da Especifica��o do caso de uso
 * [UC0366] Inserir Registro de Atendimento
 * 
 * @author Raphael Rossiter
 * @date 25/07/2006
 */
public class DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper {
	
	private Date dataPrevista;
	
	private UnidadeOrganizacional unidadeOrganizacional;
	
	private String imovelObrigatorio;
	
	private String pavimentoRuaObrigatorio;
	
	private String pavimentoCalcadaObrigatorio;
	
	private String indFaltaAgua;
	
	private String indMatricula;
	
	
	public DefinirDataPrevistaUnidadeDestinoEspecificacaoHelper(){}

	
	
	public String getPavimentoCalcadaObrigatorio() {
		return pavimentoCalcadaObrigatorio;
	}

	public void setPavimentoCalcadaObrigatorio(String pavimentoCalcadaObrigatorio) {
		this.pavimentoCalcadaObrigatorio = pavimentoCalcadaObrigatorio;
	}

	public String getPavimentoRuaObrigatorio() {
		return pavimentoRuaObrigatorio;
	}

	public void setPavimentoRuaObrigatorio(String pavimentoRuaObrigatorio) {
		this.pavimentoRuaObrigatorio = pavimentoRuaObrigatorio;
	}

	public String getImovelObrigatorio() {
		return imovelObrigatorio;
	}

	public void setImovelObrigatorio(String imovelObrigatorio) {
		this.imovelObrigatorio = imovelObrigatorio;
	}



	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}



	public String getIndFaltaAgua() {
		return indFaltaAgua;
	}



	public void setIndFaltaAgua(String indFaltaAgua) {
		this.indFaltaAgua = indFaltaAgua;
	}



	public String getIndMatricula() {
		return indMatricula;
	}



	public void setIndMatricula(String indMatricula) {
		this.indMatricula = indMatricula;
	}
	
	

}
