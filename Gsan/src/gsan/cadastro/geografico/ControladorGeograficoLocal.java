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
package gsan.cadastro.geografico;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.ControladorException;
import gsan.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorGeograficoLocal extends javax.ejb.EJBLocalObject {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param bairro
	 *            Descri��o do par�metro
	 */
    public void atualizarBairro(Bairro bairro,
    		Collection colecaoBairroArea,Collection colecaoBairroAreaRemover,
    		Usuario usuarioLogado) throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param codigoSetorComercial
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 * @exception ErroRepositorioException
	 *                Descri��o da exce��o
	 */
	public Collection pesquisarMunicipoPeloSetorComercial(
			String codigoSetorComercial, String idMunicipio)
			throws ControladorException;
	
	
	/**
	 * Verifica se o munic�pio possui CEP por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 16/05/2006
	 * 
	 * @param municipio
	 * @return boolean
	 */
	public boolean verificarMunicipioComCepPorLogradouro(Municipio municipio) throws ControladorException ;
	
	/**
	 * M�todo que retorna o maior c�digo do bairro de um munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 10/07/2006
	 * 
	 * @param idMunicipio
	 * @return
	 * @throws ControladorException
	 */
	
	public int pesquisarMaximoCodigoBairro(
			Integer idMunicipio)
			throws ControladorException;

	/**
	 * Pesquisa um munic�pio pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Munic�pio
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Municipio pesquisarObjetoMunicipioRelatorio(Integer idMunicipio)
			throws ControladorException;
	
	/**
	 * Pesquisa um bairro pelo c�digo e pelo id do munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Bairro
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Bairro pesquisarObjetoBairroRelatorio(Integer codigoBairro, Integer idMunicipio)
			throws ControladorException;
	
	/**
	 * Permite inserir um Municipio
	 * 
	 * [UC0001] Inserir Municipio
	 * 
	 * @author Kassia Albuquerque	
	 * @date 18/12/2006
	 * 
	 */
	public Integer inserirMunicipio(Municipio municipio,Usuario usuarioLogado) throws ControladorException;
	
	/**
     * [UC0035] Inserir Bairro
     * 
     * Insere um objeto do tipo bairro no BD
     * 
     * @author Vivianne Sousa
     * @date 22/12/2006
     * @param bairro
     * @param colecaoBairroArea
     * @return idBairro
     * @throws ControladorException
     */
    public Integer inserirBairro(Bairro bairro,
    		Collection colecaoBairroArea,Usuario usuarioLogado) throws ControladorException;
    
	/**
	 * @author Vivianne Sousa
	 * @date 26/12/2006
	 * 
	 * @return colecao de BairroArea
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Collection pesquisarBairroArea(Integer idBairro)
		throws ControladorException;
	
	/**
     * Remover Bairro
     * 
     * Remove os bairros e area bairro 
     * selecionados na lista da funcionalidade Manter Bairro 
     * 
     * @author Vivianne Sousa
     * @date 26/12/2006
     * @param bairro
     * @param colecaoBairroArea
     * @return idBairro
     * @throws ControladorException
     */
    public void removerBairro(String[] ids,Usuario usuarioLogado) throws ControladorException;
    
    
	 /**
	 * [UC0006] Manter Municipio
	 * 
	 * 			Filtrar Munic�pio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @param Integer
	 * @return boolean
	 */
	public boolean verificarExistenciaMunicipio(Integer codigoMunicipio)throws ControladorException;
    
	
	/**
	 * [UC0005] Manter Municipio 
	 * 			
	 * 			Remover Municipio
	 * 
	 * @author Kassia Albuquerque
	 * @date 04/01/2007
	 * 
	 * @pparam municpio
	 * @throws ControladorException
	 */
	public void removerMunicipio(String[] ids, Usuario usuarioLogado)throws ControladorException;
	
	
	/**
	 * [UC005] Manter Municipio [SB0001]Atualizar Municipio 
	 * 
	 * @author Kassia Albuquerque
	 * @date 10/01/2007
	 * 
	 * @pparam municipio
	 * @throws ControladorException
	 */
	public void atualizarMunicipio(Municipio municipio,Usuario usuarioLogado) throws ControladorException;
	
	 /**
	 * M�todo que retorna o maior id do Munic�pio
	 * 
	 * @author Rafael Corr�a
	 * @date 24/07/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */   
    public int pesquisarMaximoIdMunicipio() throws ControladorException;
    
    /**
	 * M�todo que retorna o municipio do Imovel
	 * 
	 * @author Hugo Amorim
	 * @date 27/08/2009
	 * 
	 * @return Municipio
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipioDoImovel(Integer idImovel) 
    	throws ControladorException;
	
    /**
	 * M�todo repons�vel por retornar todos os munic�pios que possuem alguma
	 * associa��o com uma localidade (localidade.muni_idprincipal != null)
	 * 
	 * @author Diogo Peixoto
	 * @date 26/04/2011
	 * 
	 * @return Collection
	 * @throws ControladorException
	 */  
    public Collection pesquisarMunicipiosAssociadoLocalidade() throws ControladorException;
    
}
