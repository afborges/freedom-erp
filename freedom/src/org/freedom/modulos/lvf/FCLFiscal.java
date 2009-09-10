/**
 * @version 05/03/2009 <BR>
 * @author Setpoint Inform�tica Ltda / Anderson Sanchez.
 * 
 * Projeto: Freedom <BR>
 * 
 * Pacote: org.freedom.modulos.std <BR>
 * Classe:
 * @(#)FVendaConsig.java <BR>
 * 
 * Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 * modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 * na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 * Este programa � distribuido na esperan�a que possa ser  util, mas SEM NENHUMA GARANTIA; <BR>
 * sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 * Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 * de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 * Cadastro de classifica��es fiscais e suas exce��es <BR>
 * 
 */

package org.freedom.modulos.lvf;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.freedom.acao.CarregaEvent;
import org.freedom.acao.CarregaListener;
import org.freedom.acao.InsertEvent;
import org.freedom.acao.InsertListener;
import org.freedom.acao.JComboBoxEvent;
import org.freedom.acao.JComboBoxListener;
import org.freedom.acao.PostEvent;
import org.freedom.acao.PostListener;
import org.freedom.acao.RadioGroupEvent;
import org.freedom.acao.RadioGroupListener;
import org.freedom.bmps.Icone;
import org.freedom.componentes.GuardaCampo;
import org.freedom.componentes.JCheckBoxPad;
import org.freedom.componentes.JComboBoxPad;
import org.freedom.componentes.JLabelPad;
import org.freedom.componentes.JPanelPad;
import org.freedom.componentes.JRadioGroup;
import org.freedom.componentes.JTabbedPanePad;
import org.freedom.componentes.JTextAreaPad;
import org.freedom.componentes.JTextFieldFK;
import org.freedom.componentes.JTextFieldPad;
import org.freedom.componentes.ListaCampos;
import org.freedom.funcoes.Funcoes;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.telas.Aplicativo;
import org.freedom.telas.FDetalhe;


public class FCLFiscal extends FDetalhe 
		implements MouseListener, ChangeListener, CarregaListener, InsertListener, RadioGroupListener, PostListener, JComboBoxListener {

	private static final long serialVersionUID = 1L;

	private JTabbedPanePad tpnPrincipal = new JTabbedPanePad();

	private JTabbedPanePad tpnGeral = new JTabbedPanePad();

	private JPanelPad panelGeral = new JPanelPad( 500, 80 );
		
	private JPanelPad panelServico = new JPanelPad( 500, 60 );
	
	private JPanelPad pnServico = new JPanelPad( new BorderLayout() );

	private JPanelPad panelVariantesCampos = new JPanelPad( 500, 80 );

	private JPanelPad panelVariantes = new JPanelPad( new GridLayout( 1, 1 ) );

	private JPanelPad panelICMS = new JPanelPad( new GridLayout( 1, 1 ) );
	
	private JPanelPad panelICMSCampos = new JPanelPad( 500, 80 );
	
	private JPanelPad panelIPI = new JPanelPad( new GridLayout( 1, 1 ) );
	
	private JPanelPad panelIPICampos = new JPanelPad( 500, 80 );
	
	private JPanelPad panelPIS = new JPanelPad( new GridLayout( 1, 1 ) );
	
	private JPanelPad panelPISCampos = new JPanelPad( 500, 80 );
	
	private JPanelPad panelCOFINS = new JPanelPad( new GridLayout( 1, 1 ) );
	
	private JPanelPad panelCOFINSCampos = new JPanelPad( 500, 80 );
	
	private JPanelPad panelNomeComum = new JPanelPad( new BorderLayout() );
	
	private JPanelPad panelNomeComumNCM = new JPanelPad( new BorderLayout() );
	
	private JPanelPad panelNomeComumNCMCampos = new JPanelPad( 500, 60 );
	
	private JSplitPane panelNomeComumNCMDescricoes = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
	
	private JPanelPad panelNomeComumNBM = new JPanelPad( 500, 60 );
	
	private JTextFieldPad txtCodFisc = new JTextFieldPad( JTextFieldPad.TP_STRING, 13, 0 );

	private JTextFieldPad txtDescFisc = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private JTextFieldPad txtCodRegra = new JTextFieldPad( JTextFieldPad.TP_STRING, 4, 0 );

	private JTextFieldFK txtDescRegra = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );
	
	private JTextFieldPad txtCodTratTrib = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescTratTrib = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0 );

	private JTextFieldPad txtCodMens = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldFK txtDescMens = new JTextFieldFK( JTextFieldPad.TP_STRING, 10000, 0 );

	private JTextFieldPad txtCodNBM = new JTextFieldPad( JTextFieldPad.TP_STRING, 12, 0 );

	private JTextFieldFK txtDescNBM = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0 );		

	private JTextFieldPad txtCodNCM = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private JTextFieldFK txtDescNCM = new JTextFieldFK( JTextFieldPad.TP_STRING, 60, 0 );

	private JTextFieldPad txtExTIPI = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );
	
	private JTextFieldPad txtCodServ = new JTextFieldPad(JTextFieldPad.TP_STRING,5,0);
	
	private JTextAreaPad txaDescServ =  new JTextAreaPad( 500 );
	
	private JTextAreaPad txaDescExTIPI = new JTextAreaPad( 1000 );
	
	private JScrollPane spDescExTIPI = new JScrollPane( txaDescExTIPI );

	private JTextAreaPad txaDescNCM = new JTextAreaPad( 2000 );
	
	private JScrollPane spDescNCM = new JScrollPane( txaDescNCM );
	
	private JTextFieldPad txtCodItClFiscal = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 5, 0 );
	
	private JTextFieldPad txtCodTipoMov = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 9, 2 );

	private JTextFieldFK txtDescTipoMov = new JTextFieldFK( JTextFieldPad.TP_STRING, 200, 0 );
	
	private JTextFieldPad txtCodTipoFisc = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );
	
	private JTextFieldFK txtDescFiscCli = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );
	
	private JTextFieldPad txtRedFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );

	private JTextFieldPad txtAliqFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );

	private JTextFieldPad txtAliqIPIFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );
	
	private JTextFieldPad txtVlrIpiUnidTrib = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );
	
	private JTextFieldPad txtVlrPisUnidTrib = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );
	
	private JTextFieldPad txtVlrCofUnidTrib = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 9, 2 );

	private JTextFieldPad txtAliqLFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 6, 2 );
	
	private JTextFieldPad txtAliqPisFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 6, 2 );
	
	private JTextFieldPad txtAliqCofinsFisc = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 6, 2 );
	
	private JTextFieldPad txtCodSitTribPIS = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );
	
	private JTextFieldPad txtImpSitTribPIS = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescSitTribPIS = new JTextFieldFK( JTextFieldPad.TP_STRING, 200, 0 );
	
	private JTextFieldPad txtCodSitTribIPI = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );
	
	private JTextFieldPad txtImpSitTribIPI = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescSitTribIPI = new JTextFieldFK( JTextFieldPad.TP_STRING, 200, 0 );
	
	private JTextFieldPad txtCodSitTribCOF = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );
	
	private JTextFieldPad txtImpSitTribCOF = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );

	private JTextFieldFK txtDescSitTribCOF = new JTextFieldFK( JTextFieldPad.TP_STRING, 200, 0 );
	
	private JCheckBoxPad cbGeralFisc = new JCheckBoxPad( "Regra geral?", "S", "N" );
	
	private JTextFieldPad txtCodPais = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldFK txtDescPais = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );
	
	private JTextFieldPad txtSiglaUF = new JTextFieldPad( JTextFieldPad.TP_STRING, 2, 0 );
	
	private JTextFieldFK txtNomeUF = new JTextFieldFK( JTextFieldPad.TP_STRING, 80, 0 ); 
	
	private JTextFieldPad txtMargemVlAgr = new JTextFieldPad( JTextFieldPad.TP_DECIMAL, 6, 2 );
	
	private JComboBoxPad cbOrig = null;
	
	private JComboBoxPad cbTpCalcIPI = null;
	
	private JComboBoxPad cbModBCICMS = null;
	
	private JComboBoxPad cbModBCICMSST = null;
	
	private JRadioGroup<String, String> rgNoUF = null;
	
	private JRadioGroup<?, ?> rgTipoFisc = null;
	
	private JRadioGroup<String, String> rgTipoST = null;
	
	private JRadioGroup<String, String> rgTpRedIcmsFisc = null;

	private JButton btCopiarVariante = new JButton( "Copiar", Icone.novo( "btExportar.gif" ) );
	
	private ListaCampos lcRegraFiscal = new ListaCampos( this, "RA" );
	
	private ListaCampos lcTratTrib = new ListaCampos( this, "TT" );

	private ListaCampos lcMens = new ListaCampos( this, "ME" );

	private ListaCampos lcNCM = new ListaCampos( this );

	private ListaCampos lcNBM = new ListaCampos( this );
	
	private ListaCampos lcUF = new ListaCampos( this );
	
	private ListaCampos lcServico = new ListaCampos( this );
	
	private ListaCampos lcTipoMov = new ListaCampos( this, "TM" );
	
	private ListaCampos lcPais = new ListaCampos( this, "" );
	
	private ListaCampos lcTipoFiscCli = new ListaCampos( this, "FC" );
	
	private ListaCampos lcSitTribIPI = new ListaCampos( this, "SI" );
		
	private ListaCampos lcSitTribPIS = new ListaCampos( this, "SP" );

	private ListaCampos lcSitTribCOF = new ListaCampos( this, "SC" );

	
	public FCLFiscal() {

		super( false );
		setTitulo( "Classifica��es Fiscais" );
		setAtribos( 50, 50, 765, 600 );

		montaCombos();
		montaListaCampos();
		montaTela();

		adicListeners();		
	}
	
	private void adicListeners() {

		// Adicionando valores padr�o
		
		rgTipoFisc.setVlrString( "TT" );
		rgTipoST.setVlrString( "SI" );
		rgTpRedIcmsFisc.setVlrString( "B" );
		
		rgTipoST.setAtivo( false );
		txtMargemVlAgr.setAtivo( false );
		cbModBCICMSST.setAtivo( false );
		rgTpRedIcmsFisc.setAtivo( false );
		txtRedFisc.setAtivo( false );
		rgTipoFisc.setAtivo( false );
		txaDescServ.setAtivo( false );
		txtVlrCofUnidTrib.setAtivo( false );
		txtVlrIpiUnidTrib.setAtivo( false );
		txtVlrPisUnidTrib.setAtivo( false );
		txtAliqIPIFisc.setAtivo( false );
		txtAliqPisFisc.setAtivo( false );
		txtAliqCofinsFisc.setAtivo( false );
				
		//Adicionando Listeners
				
		lcCampos.addCarregaListener( this );		
		lcCampos.addInsertListener( this );		
		lcCampos.addPostListener( this );
		lcTratTrib.addCarregaListener( this );
		lcSitTribPIS.addCarregaListener( this );
		lcSitTribCOF.addCarregaListener( this );
		
		lcDet.addInsertListener( this );
		
		btCopiarVariante.addActionListener( this );
		btImp.addActionListener( this );
		btPrevimp.addActionListener( this );
		
		rgTipoFisc.addRadioGroupListener( this );		
		rgTipoST.addRadioGroupListener( this );

		tpnGeral.addChangeListener( this );
		tpnPrincipal.addChangeListener( this );		
	}

	private void montaCombos() {
		
		/** Origem da mercadoria */
		
		Vector<String> vLabsOrig = new Vector<String>();		
		Vector<String> vValsOrig = new Vector<String>();
		
		vLabsOrig.addElement( "<--Selecione-->" );
		vLabsOrig.addElement( "Nacional" );
		vLabsOrig.addElement( "Estrangeira - Importa��o direta" );
		vLabsOrig.addElement( "Estrangeira - Adquirida no mercado interno" );
		vValsOrig.addElement( "" );
		vValsOrig.addElement( "0" );
		vValsOrig.addElement( "1" );
		vValsOrig.addElement( "2" );
	
		cbOrig = new JComboBoxPad( vLabsOrig, vValsOrig, JComboBoxPad.TP_STRING, 1, 0 );
		
		/** Tipo do ICMS */
	
		Vector<String> vTipoVals = new Vector<String>();
		Vector<String> vTipoLabs = new Vector<String>();
		
		vTipoLabs.addElement( "Isento" );
		vTipoLabs.addElement( "Subst. Trib." );
		vTipoLabs.addElement( "N�o inside" );
		vTipoLabs.addElement( "Trib. Integral" );
		vTipoVals.addElement( "II" );
		vTipoVals.addElement( "FF" );
		vTipoVals.addElement( "NN" );
		vTipoVals.addElement( "TT" );
		rgTipoFisc = new JRadioGroup<String, String>( 2, 2, vTipoLabs, vTipoVals );
		
		/** Tipo de substitui��o tribut�ria */
		
		Vector<String> vSTLabs = new Vector<String>();
		vSTLabs.addElement( "Substituto" );
		vSTLabs.addElement( "Substitu�do" );
		Vector<String> vSTVals = new Vector<String>();
		vSTVals.addElement( "SU" );
		vSTVals.addElement( "SI" );
		rgTipoST = new JRadioGroup<String, String>( 2, 1, vSTLabs, vSTVals );
	
		Vector<String> vTpRedIcmsFiscLabs = new Vector<String>();
		vTpRedIcmsFiscLabs.addElement( "Base ICMS" );
		vTpRedIcmsFiscLabs.addElement( "Valor ICMS" );
		Vector<String> vTpRedIcmsFiscVals = new Vector<String>();
		vTpRedIcmsFiscVals.addElement( "B" );
		vTpRedIcmsFiscVals.addElement( "V" );
		rgTpRedIcmsFisc = new JRadioGroup<String, String>( 2, 1, vTpRedIcmsFiscLabs, vTpRedIcmsFiscVals );
	
		/** Modalidade de determina��o da BC do ICMS */
		
		Vector<String> vLabsModBCICMS = new Vector<String>();		
		Vector<Integer> vValsModBCICMS = new Vector<Integer>();
		
		vLabsModBCICMS.addElement( "0-Margem Valor Agregado (%)" );
		vLabsModBCICMS.addElement( "1-Pauta (valor)" );
		vLabsModBCICMS.addElement( "2-Pre�o Tabelado M�x.(valor)" );
		vLabsModBCICMS.addElement( "3-Valor da Opera��o" );
		vValsModBCICMS.addElement( new Integer(0) );
		vValsModBCICMS.addElement( new Integer(1) );
		vValsModBCICMS.addElement( new Integer(2) );
		vValsModBCICMS.addElement( new Integer(3) );
		
		cbModBCICMS = new JComboBoxPad( vLabsModBCICMS, vValsModBCICMS, JComboBoxPad.TP_INTEGER, 1, 0 );
		cbModBCICMS.setVlrInteger( new Integer(3) );
	
		/** Modalidade de determina��o da BC do ICMS de Substitui��o tribut�ria */
		
		Vector<String> vLabsModBCICMSST = new Vector<String>();		
		Vector<Integer> vValsModBCICMSST = new Vector<Integer>();
		
		vLabsModBCICMSST.addElement( "0-Pre�o tabelado ou m�x. sugerido" );
		vLabsModBCICMSST.addElement( "1-Lista Negativa (valor)" );
		vLabsModBCICMSST.addElement( "2-Lista Positiva (valor)" );
		vLabsModBCICMSST.addElement( "3-Lista Neutra (valor)" );
		vLabsModBCICMSST.addElement( "4-Margem valor agregado (%)" );
		vLabsModBCICMSST.addElement( "5-Pauta (valor)" );
	
		vValsModBCICMSST.addElement( new Integer(0) );
		vValsModBCICMSST.addElement( new Integer(1) );
		vValsModBCICMSST.addElement( new Integer(2) );
		vValsModBCICMSST.addElement( new Integer(3) );
		vValsModBCICMSST.addElement( new Integer(4) );
		vValsModBCICMSST.addElement( new Integer(5) );
		
		cbModBCICMSST = new JComboBoxPad( vLabsModBCICMSST, vValsModBCICMSST, JComboBoxPad.TP_INTEGER, 1, 0 );		
		cbModBCICMSST.setVlrInteger( new Integer(4) );
		
		/** Tipo de c�lculo do IPI */
		
		Vector<String> vLabsTpCalcIPI = new Vector<String>();		
		Vector<String> vValsTpCalcIPI = new Vector<String>();
		
		vLabsTpCalcIPI.addElement( "<--Selecione-->" );
		vLabsTpCalcIPI.addElement( "Percentual" );
		vLabsTpCalcIPI.addElement( "Em valor" );
		vValsTpCalcIPI.addElement( "" );
		vValsTpCalcIPI.addElement( "P" );
		vValsTpCalcIPI.addElement( "V" );
	
		cbTpCalcIPI = new JComboBoxPad( vLabsTpCalcIPI, vValsTpCalcIPI, JComboBoxPad.TP_STRING, 1, 0 );
		
		cbTpCalcIPI.addComboBoxListener( this );
	}

	private void montaListaCampos() {
		
		lcRegraFiscal.add( new GuardaCampo( txtCodRegra, "CodRegra", "C�d.reg.fisc.", ListaCampos.DB_PK, null, true ) );
		lcRegraFiscal.add( new GuardaCampo( txtDescRegra, "DescRegra", "Descri��o da regra fiscal", ListaCampos.DB_SI, null, false ) );
		lcRegraFiscal.montaSql( false, "REGRAFISCAL", "LF" );
		lcRegraFiscal.setQueryCommit( false );
		lcRegraFiscal.setReadOnly( true );
		txtCodRegra.setTabelaExterna( lcRegraFiscal );

		lcNCM.setUsaME( false );
		lcNCM.add( new GuardaCampo( txtCodNCM, "CodNCM", "C�d.NCM", ListaCampos.DB_PK, txtDescNCM, false ) );
		lcNCM.add( new GuardaCampo( txtDescNCM, "DescNCM", "Descri��o da nomenclatura comum do Mercosul", ListaCampos.DB_SI, null, false ) );
		lcNCM.add( new GuardaCampo( txaDescNCM, "TextoNCM", "Descri��o completa da nomenclatura comum do Mercosul", ListaCampos.DB_SI, null, false ) );
		lcNCM.add( new GuardaCampo( txaDescExTIPI, "ExcecaoNCM", "Descri��o das exce��es", ListaCampos.DB_SI, null, false ) );
		lcNCM.setDinWhereAdic( "EXISTS(SELECT NN.CODNCM FROM LFNCMNBM NN WHERE NN.CODNCM=LFNCM.CODNCM)", txtCodNCM );
		lcNCM.montaSql( false, "NCM", "LF" );
		lcNCM.setQueryCommit( false );
		lcNCM.setReadOnly( true );
		txtCodNCM.setTabelaExterna( lcNCM );

		lcNBM.setUsaME( false );
		lcNBM.add( new GuardaCampo( txtCodNBM, "CodNBM", "C�d.NBM", ListaCampos.DB_PK, txtDescNBM, false ) );
		lcNBM.add( new GuardaCampo( txtDescNBM, "DescNBM", "Descri��o da nomenclatura brasileira de mercadorias", ListaCampos.DB_SI, null, false ) );
		lcNBM.setDinWhereAdic( "EXISTS(SELECT NN.CODNBM FROM LFNCMNBM NN WHERE NN.CODNCM = #S AND NN.CODNBM=LFNBM.CODNBM)", txtCodNCM );
		lcNBM.montaSql( false, "NBM", "LF" );
		lcNBM.setQueryCommit( false );
		lcNBM.setReadOnly( true );
		txtCodNBM.setTabelaExterna( lcNBM ); 
		
		lcTratTrib.add( new GuardaCampo( txtCodTratTrib, "CodTratTrib", "C�d.t.trib.", ListaCampos.DB_PK, null, true ) );
		lcTratTrib.add( new GuardaCampo( txtDescTratTrib, "DescTratTrib", "Descri��o do tratamento tributario", ListaCampos.DB_SI, null, false ) );
		lcTratTrib.montaSql( false, "TRATTRIB", "LF" );
		lcTratTrib.setQueryCommit( false );
		lcTratTrib.setReadOnly( true );
		txtCodTratTrib.setTabelaExterna( lcTratTrib );

		lcMens.add( new GuardaCampo( txtCodMens, "CodMens", "C�d.mens.", ListaCampos.DB_PK, null, false ) );
		lcMens.add( new GuardaCampo( txtDescMens, "Mens", "Mensagem", ListaCampos.DB_SI, null, false ) );
		lcMens.montaSql( false, "MENSAGEM", "LF" );
		lcMens.setQueryCommit( false );
		lcMens.setReadOnly( true );
		txtCodMens.setTabelaExterna( lcMens );
		
		lcTipoMov.add( new GuardaCampo( txtCodTipoMov, "CodTipoMov", "C�d.tp.Mov.", ListaCampos.DB_PK, false ) );
		lcTipoMov.add( new GuardaCampo( txtDescTipoMov, "DescTipoMov", "Descri��o do tipo de movimento", ListaCampos.DB_SI, false ) );
		lcTipoMov.montaSql( false, "TIPOMOV", "EQ" );
		lcTipoMov.setQueryCommit( false );
		lcTipoMov.setReadOnly( true );
		txtCodTipoMov.setTabelaExterna( lcTipoMov );
		
		lcTipoFiscCli.add( new GuardaCampo( txtCodTipoFisc, "CodFiscCli", "C�d.c.fisc.", ListaCampos.DB_PK, false ) );
		lcTipoFiscCli.add( new GuardaCampo( txtDescFiscCli, "DescFiscCli", "Descri��o da classifica��o fiscal", ListaCampos.DB_SI, false ) );
		lcTipoFiscCli.montaSql( false, "TIPOFISCCLI", "LF" );
		lcTipoFiscCli.setQueryCommit( false );
		lcTipoFiscCli.setReadOnly( true );
		txtCodTipoFisc.setTabelaExterna( lcTipoFiscCli );
		
		lcSitTribCOF.add( new GuardaCampo( txtCodSitTribCOF, "CodSitTrib", "C�d.sit.trib.", ListaCampos.DB_PK, false ) );
		lcSitTribCOF.add( new GuardaCampo( txtImpSitTribCOF, "ImpSitTrib", "Cofins", ListaCampos.DB_PK, false ) );
		lcSitTribCOF.add( new GuardaCampo( txtDescSitTribCOF, "DescSitTrib", "Descri��o da Situa��o Tribut�ria", ListaCampos.DB_SI, false ) );
		lcSitTribCOF.setWhereAdic( "IMPSITTRIB='CO'" );
		lcSitTribCOF.montaSql( false, "SITTRIB", "LF" );
		lcSitTribCOF.setQueryCommit( false );
		lcSitTribCOF.setReadOnly( true );
		txtCodSitTribCOF.setTabelaExterna( lcSitTribCOF );
		txtImpSitTribCOF.setTabelaExterna( lcSitTribCOF );
		
		lcSitTribPIS.add( new GuardaCampo( txtCodSitTribPIS, "CodSitTrib", "C�d.sit.trib.", ListaCampos.DB_PK, false ) );
		lcSitTribPIS.add( new GuardaCampo( txtImpSitTribPIS, "ImpSitTrib", "Pis", ListaCampos.DB_PK, false ) );
		lcSitTribPIS.add( new GuardaCampo( txtDescSitTribPIS, "DescSitTrib", "Descri��o da Situa��o Tribut�ria", ListaCampos.DB_SI, false ) );
		lcSitTribPIS.setWhereAdic( "IMPSITTRIB='PI'" );
		lcSitTribPIS.montaSql( false, "SITTRIB ", "LF" ); // Nome da tabela com espa�o em branco no final, para contornar bug do lista campos 
		lcSitTribPIS.setQueryCommit( false );
		lcSitTribPIS.setReadOnly( true );
		txtCodSitTribPIS.setTabelaExterna( lcSitTribPIS );
		txtImpSitTribPIS.setTabelaExterna( lcSitTribPIS );
		
		lcSitTribIPI.add( new GuardaCampo( txtCodSitTribIPI, "CodSitTrib", "C�d.sit.trib.", ListaCampos.DB_PK, false ) );
		lcSitTribIPI.add( new GuardaCampo( txtImpSitTribIPI, "ImpSitTrib", "IPI", ListaCampos.DB_PK, false ) );
		lcSitTribIPI.add( new GuardaCampo( txtDescSitTribIPI, "DescSitTrib", "Descri��o da Situa��o Tribut�ria", ListaCampos.DB_SI, false ) );
		lcSitTribIPI.setWhereAdic( "IMPSITTRIB='IP'" );
		lcSitTribIPI.montaSql( false, "SITTRIB  ", "LF" ); // Nome da tabela com 2 espa�os em branco no final, para contornar bug do lista campos
		lcSitTribIPI.setQueryCommit( false );
		lcSitTribIPI.setReadOnly( true );
		txtCodSitTribIPI.setTabelaExterna( lcSitTribIPI );
		txtImpSitTribIPI.setTabelaExterna( lcSitTribIPI );
		
		lcPais.setUsaME( false );
		lcPais.add( new GuardaCampo( txtCodPais, "CodPais", "Cod.pa�s.", ListaCampos.DB_PK, false ) );
		lcPais.add( new GuardaCampo( txtDescPais, "NomePais", "Nome", ListaCampos.DB_SI, false ) );
		lcPais.montaSql( false, "PAIS", "SG" );
		lcPais.setQueryCommit( false );
		lcPais.setReadOnly( true );
		txtCodPais.setTabelaExterna( lcPais );

		lcUF.setUsaME( false );		
		lcUF.add( new GuardaCampo( txtSiglaUF, "SiglaUf", "Sigla", ListaCampos.DB_PK, false ) );
		lcUF.add( new GuardaCampo( txtNomeUF, "NomeUf", "Nome", ListaCampos.DB_SI, false ) );
		lcUF.setDinWhereAdic( "CODPAIS = #N", txtCodPais );
		lcUF.montaSql( false, "UF", "SG" );
		lcUF.setQueryCommit( false );
		lcUF.setReadOnly( true );
		txtSiglaUF.setTabelaExterna( lcUF );
		
		lcServico.setUsaME( false );		
		lcServico.add( new GuardaCampo( txtCodServ, "CodServ", "Cod.Servi�o", ListaCampos.DB_PK, false ) );
		lcServico.add( new GuardaCampo( txaDescServ, "DescServ", "Descri��o do Servi�o", ListaCampos.DB_SI, false ) );
		lcServico.montaSql( false, "SERVICO", "LF" );
		lcServico.setQueryCommit( false );
		lcServico.setReadOnly( true );
		txtCodServ.setTabelaExterna( lcServico );
	}
	
	private void montaTela() {

		pnPrincipal.add( tpnPrincipal );

		lcDet.setMaster( lcCampos );


		// ********** In�cio aba Geral **********

		tpnPrincipal.addTab( "Geral", pnCliente );
		pnCab.add( panelGeral );

		setPainel( panelGeral );
		setAltCab( 130 );

		setListaCampos( lcCampos );
		
		adicCampo( txtCodFisc, 7, 20, 100, 20, "CodFisc", "C�d.class.fiscal", ListaCampos.DB_PK, true );
		adicCampo( txtDescFisc, 110, 20, 595, 20, "DescFisc", "Descri��o da classifica��o fiscal", ListaCampos.DB_SI, true );		
		adicCampo( txtCodRegra, 7, 60, 100, 20, "CodRegra", "C�d.reg.CFOP", ListaCampos.DB_FK, txtDescRegra, true );
		adicDescFK( txtDescRegra, 110, 60, 595, 20, "DescRegra", "Descri��o da regra fiscal" );		
		
		// ********** Aba Nomenclatura Comum **********

		tpnPrincipal.addTab( "Nomenclatura Comum", panelNomeComum );
		
		panelNomeComum.add( panelNomeComumNCM, BorderLayout.CENTER );
		
		panelNomeComumNCM.add( panelNomeComumNCMCampos, BorderLayout.NORTH );
		panelNomeComumNCM.add( panelNomeComumNCMDescricoes, BorderLayout.CENTER );
		
		panelNomeComumNCMDescricoes.setTopComponent( spDescNCM );
		panelNomeComumNCMDescricoes.setBottomComponent( spDescExTIPI );
		panelNomeComumNCMDescricoes.setDividerLocation( 220 );
		panelNomeComumNCMDescricoes.setDividerSize( 3 );
		panelNomeComumNCMDescricoes.setBorder( BorderFactory.createEmptyBorder() );
		
		panelNomeComum.add( panelNomeComumNBM, BorderLayout.SOUTH );
		
		spDescNCM.setBorder( BorderFactory.createTitledBorder( "Descri��o completa da nomenclatura comum do Mercosul" ) );		
		spDescExTIPI.setBorder( BorderFactory.createTitledBorder( "Texto aux�liar para exce��es" ) );		
		txaDescNCM.setBorder( BorderFactory.createEtchedBorder() );
		txaDescExTIPI.setBorder( BorderFactory.createEtchedBorder() );
		
		setPainel( panelNomeComumNCMCampos );
		
		adicCampo( txtCodNCM, 7, 20, 100, 20, "CodNCM", "C�d.NCM", ListaCampos.DB_FK, txtDescNCM, false );
		adicDescFK( txtDescNCM, 110, 20, 320, 20, "DescNCM", "Descri��o da nomenclatura comum do Mercosul" );	
		adicCampo( txtExTIPI, 433, 20, 47, 20, "ExTIPI", "C�d.ex.", ListaCampos.DB_SI, null, false );	
		
		setPainel( panelNomeComumNBM );
		
		adicCampo( txtCodNBM, 7, 25, 100, 20, "CodNBM", "C�d.NBM", ListaCampos.DB_FK, txtDescNBM, false );
		adicDescFK( txtDescNBM, 110, 25, 370, 20, "DescNBM", "Descri��o da nomenclatura brasileira de mercadorias" );		

		// *******************************
		
		// ********** Aba Servi�o ********** 

		tpnPrincipal.addTab( "Defini��o do servi�o", pnServico );
		pnServico.add( panelServico,  BorderLayout.CENTER ); 
		
		setPainel( panelServico );
		
		adicCampo( txtCodServ, 7, 20, 70, 20, "CodServ", "C�d.Serv.", ListaCampos.DB_FK, false );
		adic( txaDescServ, 80, 20, 650, 100, "Descri��o do servi�o" );	
		
		// *******************************		
		
		setListaCampos( true, "CLFISCAL", "LF" );
		lcCampos.setQueryInsert( false );

		/**  ABA VARIANTES  **/

		pnDet.add( tpnGeral );
		tpnGeral.addTab( "Variantes", panelVariantes );

		setPainel( panelVariantesCampos );
		setAltDet( 230 );

		setListaCampos( lcDet );
		setNavegador( navRod );

		Vector<String> vNoUFLabs = new Vector<String>();
		Vector<String> vNoUFVals = new Vector<String>();
		
		vNoUFLabs.addElement( "Dentro do Estado" );
		vNoUFLabs.addElement( "Fora do Estado" );
				
		vNoUFVals.addElement( "S" );
		vNoUFVals.addElement( "N" );
		
		rgNoUF = new JRadioGroup<String, String>( 2, 1, vNoUFLabs, vNoUFVals );
		rgNoUF.setVlrString( "S" );
				
		adicCampoInvisivel( txtCodItClFiscal, "CodItFisc", "Item", ListaCampos.DB_PK, true );

		adicCampo( txtCodTipoFisc, 7, 20, 70, 20, "CodFiscCli", "C�d.fisc.cli.", ListaCampos.DB_FK, txtDescFiscCli, false );
		adicDescFK( txtDescFiscCli, 80, 20, 388, 20, "DescFiscCli", "Descri��o do tipo fiscal de cliente" );

		adicCampo( txtCodTipoMov, 7, 60, 70, 20, "CodTipoMov", "C�d.tp.Mov", ListaCampos.DB_FK, txtDescTipoMov, false );
		adicDescFK( txtDescTipoMov, 80, 60, 388, 20, "DescTipoMov", "Descri��o do tipo de movimento" );
		
		adicCampo( txtCodMens, 7, 100, 70, 20, "CodMens", "C�d.mens.", ListaCampos.DB_FK, txtDescMens, false );
		adicDescFK( txtDescMens, 80, 100, 388, 20, "Mens", "Mensagem" );

		adicDB( rgNoUF, 471, 20, 150, 60, "NoUFItFisc", "Destino da mercadoria", true );
		
		adicDB( cbGeralFisc, 471,100,150,20, "GeralFisc", "Padr�o", true );
		
		adicCampo( txtCodPais, 7, 140, 70, 20, "CodPais", "Cod.pa�s", ListaCampos.DB_FK, txtDescPais, false );
		adicDescFK( txtDescPais, 80, 140, 227, 20, "NomePais", "Nome do pa�s" );
		adicCampo( txtSiglaUF, 310, 140, 70, 20, "SiglaUf", "Sigla UF", ListaCampos.DB_FK, txtNomeUF, false );
		adicDescFK( txtNomeUF, 383, 140, 238, 20, "NomeUF", "Nome UF" );
		
		adic( btCopiarVariante, 630, 135, 100, 30 );

		/**  ABA ICMS  **/

		tpnGeral.addTab( "ICMS", panelICMS );
		
		setPainel( panelICMSCampos );
		
		
		/**  Inclus�o dos campos  **/		

		adicCampo( txtCodTratTrib, 7, 20, 50, 20, "CodTratTrib", "C�d.trat.", ListaCampos.DB_FK, txtDescTratTrib, true );
		adicDescFK( txtDescTratTrib, 60, 20, 200, 20, "DescTratTrib", "Descri��o da tratamento tribut�rio" );

		adicDB( cbOrig, 7, 60, 253, 25, "OrigFisc", "Origem", true );
		adicDB( cbModBCICMS, 7, 110, 253, 25, "ModBcIcms", "Modalidade da base de c�lculo ", true );
		adicDB( cbModBCICMSST, 7, 160, 253, 25, "ModBcIcmsST", "Modalidade da base de c�lculo ST", true );
		
		JLabelPad separacao = new JLabelPad();
		separacao.setBorder( BorderFactory.createEtchedBorder() );
		adic( separacao, 272, 7, 2, 180 );
		
		adicDB( rgTipoFisc, 283, 20, 220, 70, "TipoFisc", "Situa��o do ICMS:", true );		
		adicDB( rgTipoST, 506, 20, 110, 70, "TipoST", "Tipo de Sub.Trib.", true );
		adicDB( rgTpRedIcmsFisc, 619, 20, 110, 70, "tpredicmsfisc", "Tipo de Redu��o", false );
		
		adicCampo( txtMargemVlAgr, 506, 110, 110, 20, "MargemVlAgr", "% Vlr.Agregado" , ListaCampos.DB_SI,  false );
		adicCampo( txtRedFisc, 619, 110, 110, 20, "RedFisc", "% Redu��o ICMS", ListaCampos.DB_SI, false );
		
		adicCampo( txtAliqFisc, 283, 110, 108, 20, "AliqFisc", "% Al�q.ICMS", ListaCampos.DB_SI, false );		
		adicCampo( txtAliqLFisc, 394, 110, 110, 20, "AliqlFisc", "% Aliq.liv.ICMS", ListaCampos.DB_SI, null, false );

		/**  ABA IPI  **/
		
		tpnGeral.addTab( "IPI", panelIPI );
		setPainel( panelIPICampos );
		
		adicCampo( txtCodSitTribIPI, 7, 20, 80, 20, "CodSitTribIPI", "C�d.sit.trib.", ListaCampos.DB_FK, txtDescSitTribPIS, false );
		adicCampoInvisivel( txtImpSitTribIPI, "ImpSitTribIPI", "Imposto", ListaCampos.DB_FK, false );
		adicDescFK( txtDescSitTribIPI, 90, 20, 300, 20, "DescSitTrib", "Descri��o da Situa��o Tribut�ria" );		
		adicDB( cbTpCalcIPI, 7, 60, 200, 20, "TpCalcIPI", "Tipo de c�lculo", false );
				
		adicCampo( txtAliqIPIFisc, 7, 100, 98, 20, "AliqIPIFisc", "% Al�q.IPI", ListaCampos.DB_SI, false );
		adicCampo( txtVlrIpiUnidTrib, 108, 100, 99, 20, "VlrIPIUnidTrib", "Vlr.por unidade", ListaCampos.DB_SI, false );
		
		/**  ABA PIS  **/
						
		tpnGeral.addTab( "PIS", panelPIS );
		setPainel( panelPISCampos );
		
		adicCampo( txtCodSitTribPIS, 7, 20, 80, 20, "CodSitTribPIS", "C�d.sit.trib.", ListaCampos.DB_FK, txtDescSitTribPIS, false );
		adicCampoInvisivel( txtImpSitTribPIS, "ImpSitTribPIS", "Imposto", ListaCampos.DB_FK, false );
		adicDescFK( txtDescSitTribPIS, 90, 20, 300, 20, "DescSitTrib", "Descri��o da Situa��o Tribut�ria" );		
		adicCampo( txtAliqPisFisc, 7, 60, 80, 20, "AliqPisFisc", "Aliq.PIS", ListaCampos.DB_SI, null, false );	
		adicCampo( txtVlrPisUnidTrib, 90, 60, 99, 20, "VlrPisUnidTrib", "Vlr.por unidade", ListaCampos.DB_SI, false );
		
		/**  ABA COFINS  **/
				
		tpnGeral.addTab( "COFINS", panelCOFINS );
		setPainel( panelCOFINSCampos );
		
		adicCampo( txtCodSitTribCOF, 7, 20, 80, 20, "CodSitTribCOF", "C�d.sit.trib.", ListaCampos.DB_FK, txtDescSitTribCOF, false );	
		adicCampoInvisivel( txtImpSitTribCOF, "ImpSitTribCOF", "Imposto", ListaCampos.DB_FK, false );
		adicDescFK( txtDescSitTribCOF, 90, 20, 300, 20, "DescSitTrib", "Descri��o da Situa��o Tribut�ria" );		
	
		adicCampo( txtAliqCofinsFisc, 7, 60, 80, 20, "AliqCofinsFisc", "Aliq.Cofins", ListaCampos.DB_SI, null, false );	
		adicCampo( txtVlrCofUnidTrib, 90, 60, 99, 20, "VlrCofUnidTrib", "Vlr.por unidade", ListaCampos.DB_SI, false );
			
		setListaCampos( true, "ITCLFISCAL", "LF" );
		lcDet.setQueryInsert( false );
		
		montaTab();

		tab.setTamColuna( 30, 0 ); // �tem
		tab.setColunaInvisivel( 1 ); //Cod. fisc. cli
		tab.setTamColuna( 150, 2 ); // Desc fisc. cli
		tab.setTamColuna( 30, 3 ); // cod. tipomov
		tab.setTamColuna( 150, 4 ); // desc. tipomo
		tab.setColunaInvisivel( 5 ); // cod. mensagem
		tab.setTamColuna( 150, 6 ); // desc. mensagem
		tab.setTamColuna( 30, 7 ); // Destino
		tab.setTamColuna( 30, 8 ); // Padr�o
		tab.setColunaInvisivel( 9 ); // cod.trat.trib.
		tab.setColunaInvisivel( 10 ); // desc.trat.trib.
		tab.setColunaInvisivel( 11 ); // origem
		tab.setColunaInvisivel( 12 ); // situa��o tributaria
		tab.setColunaInvisivel( 13 ); // tipo de substitui��o
		tab.setColunaInvisivel( 14 ); // tipo de redu��o
		tab.setTamColuna( 50, 15 ); // IVA
		tab.setTamColuna( 50, 16 ); // % redu��o
		tab.setTamColuna( 50, 17 ); // % ICMS
		tab.setTamColuna( 50, 18 ); // % Liv. ICMS
		tab.setTamColuna( 50, 19 ); // % IPI
		tab.setColunaInvisivel( 20 ); // % C�d.sittrib.pis
		tab.setColunaInvisivel( 21 ); // % Imposto PIS
		tab.setColunaInvisivel( 22 ); // % Desc. Sit. Trib. PIS
		tab.setTamColuna( 50, 23 ); // % Aliq. PIS
		tab.setColunaInvisivel( 24 ); // % C�d.sittrib.COFINS
		tab.setColunaInvisivel( 25 ); // % Imposto COFINS
		tab.setColunaInvisivel( 26 ); // % Desc. Sit. Trib. COFINS
		tab.setTamColuna( 50, 27 ); // % Aliq. COFINS
		
		panelVariantes.add( panelVariantesCampos );
		panelICMS.add( panelICMSCampos );
		panelIPI.add( panelIPICampos );
		panelPIS.add( panelPISCampos );
		panelCOFINS.add( panelCOFINSCampos );		
	}
	
	private void copiarVariante() {
		
		if ( txtCodItClFiscal.getVlrInteger() == 0 ) {
			Funcoes.mensagemInforma( this, "Selecione o item a ser copiado" );
			return;
		}
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append( "INSERT INTO LFITCLFISCAL ( CODEMP, CODFILIAL, CODFISC, " );
			sql.append( "CODITFISC, ORIGFISC, CODEMPTT, CODFILIALTT, TIPOFISC, TPREDICMSFISC, REDFISC, " );
			sql.append( "CODTRATTRIB, NOUFITFISC, CODEMPFC, CODFILIALFC, CODFISCCLI, ALIQFISC, ALIQLFISC, " );
			sql.append( "ALIQIPIFISC, ALIQPISFISC, ALIQCOFINSFISC, CODEMPME, CODFILIALME, CODMENS, " );
			sql.append( "CODEMPTM, CODFILIALTM, CODTIPOMOV, TIPOST, MARGEMVLAGR, GERALFISC, DTINS, " );
			sql.append( "IDUSUINS, DTALT, IDUSUALT, HINS, HALT, CODEMPSP, CODFILIALSP, CODSITTRIBPIS, " );
			sql.append( "IMPSITTRIBPIS, CODEMPSC, CODFILIALSC, CODSITTRIBCOF, IMPSITTRIBCOF, CODEMPSI, " );
			sql.append( "CODFILIALSI, CODSITTRIBIPI, IMPSITTRIBIPI, TPCALCIPI, VLRIPIUNIDTRIB, MODBCICMS, " );
			sql.append( "MODBCICMSST, CODPAIS, SIGLAUF, VLRPISUNIDTRIB, VLRCOFUNIDTRIB) " );
			sql.append( "SELECT CODEMP, CODFILIAL, CODFISC, " );
			sql.append( "(SELECT MAX(CODITFISC)+1 FROM LFITCLFISCAL WHERE CODEMP=I.CODEMP AND CODFILIAL=I.CODFILIAL AND CODFISC=I.CODFISC), " ); 
			sql.append( "ORIGFISC, CODEMPTT, CODFILIALTT, TIPOFISC, TPREDICMSFISC, REDFISC, " );
			sql.append( "CODTRATTRIB, NOUFITFISC, CODEMPFC, CODFILIALFC, CODFISCCLI, ALIQFISC, ALIQLFISC, " );
			sql.append( "ALIQIPIFISC, ALIQPISFISC, ALIQCOFINSFISC, CODEMPME, CODFILIALME, CODMENS, " );
			sql.append( "CODEMPTM, CODFILIALTM, CODTIPOMOV, TIPOST, MARGEMVLAGR, GERALFISC, DTINS, " );
			sql.append( "IDUSUINS, DTALT, IDUSUALT, HINS, HALT, CODEMPSP, CODFILIALSP, CODSITTRIBPIS, " );
			sql.append( "IMPSITTRIBPIS, CODEMPSC, CODFILIALSC, CODSITTRIBCOF, IMPSITTRIBCOF, CODEMPSI, " );
			sql.append( "CODFILIALSI, CODSITTRIBIPI, IMPSITTRIBIPI, TPCALCIPI, VLRIPIUNIDTRIB, MODBCICMS, " );
			sql.append( "MODBCICMSST, CODPAIS, SIGLAUF, VLRPISUNIDTRIB, VLRCOFUNIDTRIB " );
			sql.append( "FROM LFITCLFISCAL I " );
			sql.append( "WHERE I.CODEMP=? AND I.CODFILIAL=? AND I.CODFISC=? AND I.CODITFISC=? " );
			
			PreparedStatement ps = con.prepareStatement( sql.toString() );
			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "LFITCLFISCAL" ) );
			ps.setString( 3, txtCodFisc.getVlrString() );
			ps.setInt( 4, txtCodItClFiscal.getVlrInteger() );
			
			ps.executeUpdate();
			
			con.commit();
			
			Funcoes.mensagemInforma( this, "Item copiado com sucesso." );

			lcCampos.carregaDados();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
			Funcoes.mensagemErro( this, "Erro ao copiar item!\n" + e.getMessage(), true, con, e );			
		}
		
	}
	
	public void actionPerformed( ActionEvent e ) {
		
		if ( e.getSource() == btCopiarVariante ) {
			copiarVariante();
		}
		else {
			super.actionPerformed( e );
		}
	}

	public void mouseClicked( MouseEvent e ) {
		if ( e.getClickCount() == 2 ) { }
	}

	public void mouseEntered( MouseEvent e ) { }

	public void mouseExited( MouseEvent e ) { }

	public void mousePressed( MouseEvent e ) { }

	public void mouseReleased( MouseEvent e ) { }

	public void stateChanged( ChangeEvent e ) {	}

	public void beforeCarrega( CarregaEvent e ) { }

	public void afterCarrega( CarregaEvent e ) { 
		
		if ( e.getListaCampos() == lcTratTrib ) {
			// Redu��o na base de calculo.
			if ( "20".equals( txtCodTratTrib.getVlrString() ) || "51".equals( txtCodTratTrib.getVlrString() ) ) {
				rgTpRedIcmsFisc.setAtivo( true );
				txtRedFisc.setAtivo( true );
				rgTipoFisc.setVlrString( "TT" );
			}
			else {

				rgTpRedIcmsFisc.setAtivo( false );
				txtRedFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtRedFisc.setAtivo( false );

				// Substitui��o tribut�ria
				if ( "10".equals( txtCodTratTrib.getVlrString() ) ) {
					rgTipoST.setAtivo( true );
					rgTipoFisc.setVlrString( "FF" );
				}
				else {
					rgTipoST.setAtivo( false );

					// Tributado integralmente
					if ( "00".equals( txtCodTratTrib.getVlrString() ) ) {
						rgTipoFisc.setVlrString( "TT" );
					}
					// Isento ou n�o tribut.
					else if ( "30".equals( txtCodTratTrib.getVlrString() ) || "40".equals( txtCodTratTrib.getVlrString() ) ) {
						rgTipoFisc.setVlrString( "II" );
						txtAliqFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
					}
					// N�o insid�ncia
					else if ( "41".equals( txtCodTratTrib.getVlrString() ) || "50".equals( txtCodTratTrib.getVlrString() ) ) {
						rgTipoFisc.setVlrString( "NN" );
						txtAliqFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
					}
				}
			}
		}
		else if ( e.getListaCampos() == lcSitTribPIS ) {
			if ( "03".equals( txtCodSitTribPIS.getVlrString() ) ) {
				txtVlrPisUnidTrib.setAtivo( true );
				txtAliqPisFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtAliqPisFisc.setAtivo( false );
			}
			else {
				txtAliqPisFisc.setAtivo( true );
				txtVlrPisUnidTrib.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtVlrPisUnidTrib.setAtivo( false );
			}
		}
		else if ( e.getListaCampos() == lcSitTribCOF ) {
			if ( "03".equals( txtCodSitTribCOF.getVlrString() ) ) {
				txtVlrCofUnidTrib.setAtivo( true );
				txtAliqCofinsFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtAliqCofinsFisc.setAtivo( false );
			}
			else {
				txtAliqCofinsFisc.setAtivo( true );
				txtVlrCofUnidTrib.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtVlrCofUnidTrib.setAtivo( false );
			}
		}
	}

	public void beforeInsert( InsertEvent e ) { }

	public void afterInsert( InsertEvent e ) { }

	public void beforePost( PostEvent e ) {
		
		if ( e.getListaCampos() == lcCampos ) {
			if ( txtCodNCM.getVlrString().trim().length() > 0 && txtCodNBM.getVlrString().trim().length() == 0 ) {
				lcCampos.cancelPost();
				Funcoes.mensagemInforma( this, "A nomenclatura brasileira de mercadorias deve estar amarrada a nomenclatura comum do mercosul!" );
				txtCodNBM.requestFocus();
			}
		}
		
		super.beforePost( e );
	}

	public void valorAlterado( RadioGroupEvent e ) {
		
		if ( e.getSource() == rgTipoFisc ) {
			if ( "FF".equals( rgTipoFisc.getVlrString() ) ) { // Caso seja substitui��o tribut�ria
				rgTipoST.setAtivo( true );
			}
			else {
				rgTipoST.setVlrString( "SI" );
				rgTipoST.setAtivo( false );
			}
		}
		else if ( e.getSource() == rgTipoST || e.getSource() == rgTipoFisc ) {
			if ( "SU".equals( rgTipoST.getVlrString() ) && "FF".equals( rgTipoFisc.getVlrString() ) ) { // Substitu�do
				txtMargemVlAgr.setAtivo( true );
				cbModBCICMSST.setAtivo( true );
			}
			else {
				txtMargemVlAgr.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtMargemVlAgr.setAtivo( false );
				cbModBCICMSST.setAtivo( false );
			}
		}
	}
	
	public void valorAlterado( JComboBoxEvent evt ) {

		if ( evt.getComboBoxPad() == cbTpCalcIPI ) {
			if ( "V".equals( cbTpCalcIPI.getVlrString() ) ) {
				txtAliqIPIFisc.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtAliqIPIFisc.setAtivo( false );
				txtVlrIpiUnidTrib.setAtivo( true );
			}
			else if ( "P".equals( cbTpCalcIPI.getVlrString() ) ) {
				txtVlrIpiUnidTrib.setVlrBigDecimal( new BigDecimal( 0 ) );
				txtVlrIpiUnidTrib.setAtivo( false );
				txtAliqIPIFisc.setAtivo( true );
			}
		}		
	}

	public void setConexao( DbConnection con ) {
	
		super.setConexao( con );
		
		lcRegraFiscal.setConexao( con );
		lcNBM.setConexao( con );
		lcNCM.setConexao( con );
		lcTratTrib.setConexao( con );
		lcMens.setConexao( con );
		lcTipoMov.setConexao( con );
		lcTipoFiscCli.setConexao( con );
		lcSitTribCOF.setConexao( con );
		lcSitTribPIS.setConexao( con );
		lcSitTribIPI.setConexao( con );
		lcPais.setConexao( con );
		lcUF.setConexao( con );
		lcServico.setConexao( con );
	}

	
}
