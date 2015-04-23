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
package gsan.gui.faturamento;

import gsan.cadastro.imovel.Imovel;
import gsan.fachada.Fachada;
import gsan.gui.GcomAction;
import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CalcularPrestacaoDebitoACobrarAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirDebitoACobrar");
        
		HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    	
        InserirDebitoACobrarActionForm inserirDebitoACobrarActionForm = (InserirDebitoACobrarActionForm) actionForm;
        BigDecimal valorEntrada = null;
        
        if(inserirDebitoACobrarActionForm.getValorEntrada() != null  && !inserirDebitoACobrarActionForm.getValorEntrada().equals("")){
        	String valEntrada = inserirDebitoACobrarActionForm.getValorEntrada();
        	valorEntrada = Util.formatarMoedaRealparaBigDecimal(valEntrada);
        }else{
        	valorEntrada = new BigDecimal("0.00");
        }
        
        BigDecimal valorTotalServico = Util.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm.getValorTotalServico());
        
        BigDecimal percentualAbatimento;
        if(inserirDebitoACobrarActionForm.getPercentualAbatimento() != null && !inserirDebitoACobrarActionForm.getPercentualAbatimento().equals("")){
        	String percAbatimento = inserirDebitoACobrarActionForm.getPercentualAbatimento();
        	percentualAbatimento =  Util.formatarMoedaRealparaBigDecimal(percAbatimento);	
        }else{
        	percentualAbatimento = new BigDecimal("0.00");
        }
        
    	String taxaJurosFinanciamento = inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento();
    	BigDecimal taxaJutosFinanciamento = new BigDecimal("0.00");
    	if(taxaJurosFinanciamento != null && !taxaJurosFinanciamento.equals("")){
    		taxaJutosFinanciamento =  Util.formatarMoedaRealparaBigDecimal(taxaJurosFinanciamento);
    	}
    	
        Integer numeroPrestacoes = new Integer(inserirDebitoACobrarActionForm.getNumeroPrestacoes());
		
        Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}
        
        //Chamar o CalcularPrestacao
        ArrayList valores = fachada.calcularValorPrestacao(taxaJutosFinanciamento,numeroPrestacoes,valorTotalServico,
        		valorEntrada,percentualAbatimento,inserirDebitoACobrarActionForm.getIdTipoDebito(),null,imovel, usuario);
        
        
        BigDecimal valorTotal = new BigDecimal(valores.get(0).toString());
        
        inserirDebitoACobrarActionForm.setValorTotalServico(Util.formatarMoedaReal(valorTotal));
        inserirDebitoACobrarActionForm.setValorPrestacao(valores.get(1).toString().replace('.',','));
        inserirDebitoACobrarActionForm.setValorJuros(valores.get(2).toString().replace('.',','));
        inserirDebitoACobrarActionForm.setvalorTotalServicoAParcelar(valores.get(3).toString().replace('.',','));
        
        return retorno;
    }
    
}
