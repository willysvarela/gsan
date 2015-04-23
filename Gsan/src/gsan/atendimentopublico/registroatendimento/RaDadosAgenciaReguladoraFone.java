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
package gsan.atendimentopublico.registroatendimento;

import gsan.cadastro.cliente.FoneTipo;
import gsan.interceptor.ObjetoTransacao;
import gsan.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RaDadosAgenciaReguladoraFone extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short ddd;

    /** nullable persistent field */
    private String fone;

    /** nullable persistent field */
    private String ramal;

    /** nullable persistent field */
    private Short indicadoFonePadrao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private FoneTipo foneTipo;

    /** persistent field */
    private gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora;

    /** full constructor */
    public RaDadosAgenciaReguladoraFone(Short ddd, String fone, String ramal, Short indicadoFonePadrao, Date ultimaAlteracao, FoneTipo foneTipo, gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.ddd = ddd;
        this.fone = fone;
        this.ramal = ramal;
        this.indicadoFonePadrao = indicadoFonePadrao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.foneTipo = foneTipo;
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
    }

    /** default constructor */
    public RaDadosAgenciaReguladoraFone() {
    }

    /** minimal constructor */
    public RaDadosAgenciaReguladoraFone(FoneTipo foneTipo, gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.foneTipo = foneTipo;
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getDdd() {
        return this.ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public String getFone() {
        return this.fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getRamal() {
        return this.ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public Short getIndicadoFonePadrao() {
        return this.indicadoFonePadrao;
    }

    public void setIndicadoFonePadrao(Short indicadoFonePadrao) {
        this.indicadoFonePadrao = indicadoFonePadrao;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public FoneTipo getFoneTipo() {
        return this.foneTipo;
    }

    public void setFoneTipo(FoneTipo foneTipo) {
        this.foneTipo = foneTipo;
    }

    public gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora getRaDadosAgenciaReguladora() {
        return this.raDadosAgenciaReguladora;
    }

    public void setRaDadosAgenciaReguladora(gsan.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
        this.raDadosAgenciaReguladora = raDadosAgenciaReguladora;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	    
    public FiltroRaDadosAgenciaReguladoraFone  retornaFiltro() {
		FiltroRaDadosAgenciaReguladoraFone filtroRaDadosAgenciaReguladoraFone = new FiltroRaDadosAgenciaReguladoraFone();

		filtroRaDadosAgenciaReguladoraFone.adicionarParametro(new ParametroSimples(FiltroRaDadosAgenciaReguladoraFone.ID,this.getId()));
		filtroRaDadosAgenciaReguladoraFone.adicionarCaminhoParaCarregamentoEntidade("raDadosAgenciaReguladora");
		filtroRaDadosAgenciaReguladoraFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");
		
		
		return filtroRaDadosAgenciaReguladoraFone;
	}

}
