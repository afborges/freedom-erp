/**
 * @version 28/01/2005 <BR>
 * @author Setpoint Inform�tica Ltda./Anderson Sanchez <BR>
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.grh <BR>
 * Classe:
 * @(#)FArea.java <BR>
 * 
 * Este programa � licenciado de acordo com a LPG-PC (Licen�a P�blica Geral para Programas de Computador), <BR>
 * vers�o 2.1.0 ou qualquer vers�o posterior. <BR>
 * A LPG-PC deve acompanhar todas PUBLICA��ES, DISTRIBUI��ES e REPRODU��ES deste Programa. <BR>
 * Caso uma c�pia da LPG-PC n�o esteja dispon�vel junto com este Programa, voc� pode contatar <BR>
 * o LICENCIADOR ou ent�o pegar uma c�pia em: <BR>
 * Licen�a: http://www.lpg.adv.br/licencas/lpgpc.rtf <BR>
 * Para poder USAR, PUBLICAR, DISTRIBUIR, REPRODUZIR ou ALTERAR este Programa � preciso estar <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Formul�rio para cadastro das �reas de conhecimento para uso nas fun��es de recrutamento e sele��o.
 * 
 */

package org.freedom.modulos.grh;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperPrintManager;

import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FDados;
import org.freedom.telas.FPrinterJob;

public class FArea extends FDados {

	private static final long serialVersionUID = 1L;

	private final JTextFieldPad txtCodArea = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 5, 0 );

	private final JTextFieldPad txtDescArea = new JTextFieldPad( JTextFieldPad.TP_STRING, 60, 0 );

	public FArea() {

		super();
		setTitulo( "Cadastro de Areas" );
		setAtribos( 50, 50, 360, 125 );

		montaTela();
				
		btImp.addActionListener( this );
		btPrevimp.addActionListener( this );		
		setImprimir( true );
	}

	private void montaTela() {

		adicCampo( txtCodArea, 7, 20, 70, 20, "CodArea", "C�d.area", ListaCampos.DB_PK, true );
		adicCampo( txtDescArea, 80, 20, 250, 20, "DescArea", "Descri��o da area", ListaCampos.DB_SI, true );
		setListaCampos( true, "AREA", "RH" );
		lcCampos.setQueryInsert( false );
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btPrevimp ) {
			imprimir( true );
		}
		else if ( evt.getSource() == btImp ) {
			imprimir( false );
		}
		
		super.actionPerformed( evt );
	}

	private void imprimir( boolean bVisualizar ) {
		
		FPrinterJob dlGr = null;
		HashMap<String, Object> hParam = new HashMap<String, Object>();

		hParam.put( "CODEMP", Aplicativo.iCodEmp );
		hParam.put( "CODFILIAL", ListaCampos.getMasterFilial( "RHAREA" ) );

		dlGr = new FPrinterJob( "relatorios/grhArea.jasper", "Lista de �reas", "", this, hParam, con, null, false );

		if ( bVisualizar ) {
			dlGr.setVisible( true );
		}
		else {
			try {
				JasperPrintManager.printReport( dlGr.getRelatorio(), true );
			} catch ( Exception e ) {
				e.printStackTrace();
				Funcoes.mensagemErro( this, "Erro na gera��o do rel�torio!" + e.getMessage(), true, con, e );
			}
		}
	}
}
