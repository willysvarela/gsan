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
package gcom.micromedicao;

import gcom.util.Util;

import java.io.Serializable;
import java.util.Date;

public class TelemetriaLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Short PARCIAL = new Short("1");
	public static final Short TOTAL = new Short("2");
    
    private Integer id;
    private String conteudoStringEnviada;
    private Short indicadorLogErro;
    private Short indicadorRejeicaoParcial;
    private Date recepcao;
    private Date ultimaAlteracao;
	
    public TelemetriaLog() {}

	public TelemetriaLog(Integer id, String conteudoStringEnviada,
			Short indicadorLogErro, Short indicadorRejeicaoParcial,
			Date recepcao, Date ultimaAlteracao) {
		super();
		this.id = id;
		this.conteudoStringEnviada = conteudoStringEnviada;
		this.indicadorLogErro = indicadorLogErro;
		this.indicadorRejeicaoParcial = indicadorRejeicaoParcial;
		this.recepcao = recepcao;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudoStringEnviada() {
		return conteudoStringEnviada;
	}

	public void setConteudoStringEnviada(String conteudoStringEnviada) {
		this.conteudoStringEnviada = conteudoStringEnviada;
	}

	public Short getIndicadorLogErro() {
		return indicadorLogErro;
	}

	public void setIndicadorLogErro(Short indicadorLogErro) {
		this.indicadorLogErro = indicadorLogErro;
	}

	public Short getIndicadorRejeicaoParcial() {
		return indicadorRejeicaoParcial;
	}

	public void setIndicadorRejeicaoParcial(Short indicadorRejeicaoParcial) {
		this.indicadorRejeicaoParcial = indicadorRejeicaoParcial;
	}

	public Date getRecepcao() {
		return recepcao;
	}

	public void setRecepcao(Date recepcao) {
		this.recepcao = recepcao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	public String getConteudoStringEnviadaTruncada() {
		if(getConteudoStringEnviada() != null){
			return Util.truncarString(getConteudoStringEnviada(), 54);
		}
		return "";
	}
}
