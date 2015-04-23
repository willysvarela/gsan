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
package gsan.cadastro.atualizacaocadastralsimplificado;

import gsan.seguranca.acesso.usuario.Usuario;
import gsan.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class AtualizacaoCadastralSimplificado {

	private Integer id;
	private String nome;
	private String comentario;
	private AtualizacaoCadastralSimplificadoBinario binario;
	private Usuario usuario;
	private Integer qtdeTotalImoveis;
	private Integer qtdeImoveisComHidrometro;
	private Integer qtdeImoveisComHidrometroAtualizados;
	private Integer qtdeImoveisSemHidrometro;
	private Integer qtdeImoveisComEconomiasAtualizados;
	private Integer qtdeImoveisComMedidorEnergiaAtualizados;
	private Integer qtdeImoveisComClienteAtualizados;
	private Date ultimaAlteracao;
	private Collection<AtualizacaoCadastralSimplificadoLinha> linhas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public AtualizacaoCadastralSimplificadoBinario getBinario() {
		return binario;
	}

	public void setBinario(AtualizacaoCadastralSimplificadoBinario binario) {
		this.binario = binario;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getQtdeTotalImoveis() {
		return qtdeTotalImoveis;
	}

	public void setQtdeTotalImoveis(Integer qtdeTotalImoveis) {
		this.qtdeTotalImoveis = qtdeTotalImoveis;
	}

	public Integer getQtdeImoveisComHidrometro() {
		return qtdeImoveisComHidrometro;
	}

	public void setQtdeImoveisComHidrometro(Integer qtdeImoveisComHidrometro) {
		this.qtdeImoveisComHidrometro = qtdeImoveisComHidrometro;
	}

	public Integer getQtdeImoveisComHidrometroAtualizados() {
		return qtdeImoveisComHidrometroAtualizados;
	}

	public void setQtdeImoveisComHidrometroAtualizados(
			Integer qtdeImoveisComHidrometroAtualizados) {
		this.qtdeImoveisComHidrometroAtualizados = qtdeImoveisComHidrometroAtualizados;
	}

	public Integer getQtdeImoveisSemHidrometro() {
		return qtdeImoveisSemHidrometro;
	}

	public void setQtdeImoveisSemHidrometro(Integer qtdeImoveisSemHidrometro) {
		this.qtdeImoveisSemHidrometro = qtdeImoveisSemHidrometro;
	}

	public Integer getQtdeImoveisComEconomiasAtualizados() {
		return qtdeImoveisComEconomiasAtualizados;
	}

	public void setQtdeImoveisComEconomiasAtualizados(
			Integer qtdeImoveisComEconomiasAtualizados) {
		this.qtdeImoveisComEconomiasAtualizados = qtdeImoveisComEconomiasAtualizados;
	}

	public Integer getQtdeImoveisComMedidorEnergiaAtualizados() {
		return qtdeImoveisComMedidorEnergiaAtualizados;
	}

	public void setQtdeImoveisComMedidorEnergiaAtualizados(
			Integer qtdeImoveisComMedidorEnergiaAtualizados) {
		this.qtdeImoveisComMedidorEnergiaAtualizados = qtdeImoveisComMedidorEnergiaAtualizados;
	}


	public void setQtdeImoveisComClienteAtualizados(
			Integer qtdeImoveisComClienteAtualizados) {
		this.qtdeImoveisComClienteAtualizados = qtdeImoveisComClienteAtualizados;
	}

	public Integer getQtdeImoveisComClienteAtualizados() {
		return qtdeImoveisComClienteAtualizados;
	}
	
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Collection<AtualizacaoCadastralSimplificadoLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(Collection<AtualizacaoCadastralSimplificadoLinha> linhas) {
		this.linhas = linhas;
	}

	public String getIndiceAtualizacaoHidrometro() {
		if (qtdeImoveisComHidrometro != null && qtdeImoveisComHidrometro > 0) {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComHidrometroAtualizados).divide(
				new BigDecimal(this.qtdeImoveisComHidrometro), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
		} else {
			return Util.formataBigDecimal(BigDecimal.ZERO, 2, true);
		}
	}

	public String getIndiceAtualizacaoEconomias() {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComEconomiasAtualizados).divide(
				new BigDecimal(this.qtdeTotalImoveis), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
	}
	
	public String getIndiceAtualizacaoMedidoresEnergia() {
		BigDecimal indice = new BigDecimal(0);
		if (qtdeImoveisComMedidorEnergiaAtualizados != null){
			indice = new BigDecimal(
					this.qtdeImoveisComMedidorEnergiaAtualizados).divide(
							new BigDecimal(this.qtdeTotalImoveis), 4,
							BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		
		return Util.formataBigDecimal(indice, 2, true);
	}
	
	public String getIndiceAtualizacaoClientes() {
		BigDecimal indice = new BigDecimal(0);
		if (qtdeImoveisComClienteAtualizados != null){
			indice = new BigDecimal(
					this.qtdeImoveisComClienteAtualizados).divide(
							new BigDecimal(this.qtdeTotalImoveis), 4,
							BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		
		return Util.formataBigDecimal(indice, 2, true);
	}

}
