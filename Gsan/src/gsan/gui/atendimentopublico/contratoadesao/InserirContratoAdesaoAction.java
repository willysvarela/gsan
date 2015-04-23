/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
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

package gsan.gui.atendimentopublico.contratoadesao;

import gsan.atendimentopublico.contratoadesao.ContratoAdesao;
import gsan.atendimentopublico.contratoadesao.FiltroContratoAdesao;
import gsan.gui.ActionServletException;
import gsan.gui.GcomAction;
import gsan.util.ConstantesSistema;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;
import gsan.util.filtro.ParametroSimples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class InserirContratoAdesaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
			// Seta o caminho de retorno
			ActionForward retorno = actionMapping.findForward("telaSucesso");
				
					InserirContratoAdesaoActionForm inserirContratoAdesaoActionForm = (InserirContratoAdesaoActionForm) actionForm;

					// Mudar isso quando houver esquema de seguran�a
					HttpSession sessao = httpServletRequest.getSession(false);
					
					
					String descricao = inserirContratoAdesaoActionForm.getDescricao();
					String descricaoAbreviada = inserirContratoAdesaoActionForm.getDescricaoAbreviada();
			
					
					
					ContratoAdesao contratoAdesao = new ContratoAdesao();
					Collection colecaoPesquisa = null;

					// Verifica se a Descri��o foi passada
					if (!"".equals(descricao)) {
						contratoAdesao.setDescricao(descricao);
					} else {
						throw new ActionServletException("atencao.required", null,
								"Descri��o");
					}
					
					
					// Verifica se a Descri��o abreviada foi passada
					if (!"".equals(descricaoAbreviada)) {
						contratoAdesao.setDescricaoAbreviada(descricaoAbreviada);
					} else {
						throw new ActionServletException("atencao.required", null,
								"Descri��o Abreviada");
					}
					
					//Verifica se o arquivo (.PDF) foi passado
					
					if(inserirContratoAdesaoActionForm.getArquivo().getFileName() != null && !inserirContratoAdesaoActionForm.getArquivo().getFileName().equals("") ){
						
						if(inserirContratoAdesaoActionForm.getArquivo().getFileName().toUpperCase().contains(".PDF") )
						{
							try {
								contratoAdesao.setImagemArquivo(inserirContratoAdesaoActionForm.getArquivo().getFileData());
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							
							throw new ActionServletException(
								"atencao.required",
								null, "apenas arquivo em formato .pdf");
						}		
					
							
					
					}else{
						
						throw new ActionServletException(
							"atencao.required",
							null, "um arquivo .pdf");
					}
					
					contratoAdesao.setDescricao(descricao);
					contratoAdesao.setDescricaoAbreviada(descricaoAbreviada);
					contratoAdesao.setIndicadorUso(ConstantesSistema.SIM);
					contratoAdesao.setUltimaAlteracao(new Date());
					
					try {
						
						contratoAdesao.setImagemArquivo(inserirContratoAdesaoActionForm.getArquivo().getFileData());
					
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					FiltroContratoAdesao filtroContratoAdesao = new FiltroContratoAdesao();

					filtroContratoAdesao.adicionarParametro(
						new ParametroSimples(
							FiltroContratoAdesao.DESCRICAO, 
							contratoAdesao.getDescricao()));
					
										
					colecaoPesquisa = 
						(Collection) this.getFachada().pesquisar(
							filtroContratoAdesao, ContratoAdesao.class.getName());

					if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
									
						// Caso j� haja um Contrato de Ades�o com a Descri��o 
						throw new ActionServletException("atencao.contrato_adesao_ja_cadastrado", 
							null,contratoAdesao.getDescricao());
					} else {

					Integer id= (Integer) this.getFachada().inserir(contratoAdesao);

						montarPaginaSucesso(httpServletRequest,
							"Contrato de Adesao " + descricao+ " inserido com sucesso.",
							"Inserir Outro Contrato de Adesao",
							"exibirInserirContratoAdesaoAction.do?menu=sim",
							"exibirAtualizarContratoAdesaoAction.do?idRegistroAtualizar=" + id+"&primeiraVez=Ok",
							"Atualizar Contrato de Adesao Inserido");
						
						
						sessao.removeAttribute("InserirContratoAdesaoActionForm");

						return retorno;
			
					}

				
		}
	
	
}
