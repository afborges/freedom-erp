/**
 * @version 14/07/2003 <BR>
 * @author Setpoint Inform�tica Ltda./Fernando Oliveira da Silva <BR>
 * 
 *         Projeto: Freedom <BR>
 * 
 *         Pacote: org.freedom.modulos.std <BR>
 *         Classe:
 * @(#)DLEditaPag.java <BR>
 * 
 *                     Este arquivo � parte do sistema Freedom-ERP, o Freedom-ERP � um software livre; voc� pode redistribui-lo e/ou <BR>
 *                     modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); <BR>
 *                     na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o. <BR>
 *                     Este programa � distribuido na esperan�a que possa ser util, mas SEM NENHUMA GARANTIA; <BR>
 *                     sem uma garantia implicita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. <BR>
 *                     Veja a Licen�a P�blica Geral GNU para maiores detalhes. <BR>
 *                     Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU junto com este programa, se n�o, <BR>
 *                     de acordo com os termos da LPG-PC <BR>
 * <BR>
 * 
 *                     Coment�rios sobre a classe...
 */

package org.freedom.modulos.fnc.view.dialog.utility;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;

import org.freedom.acao.CarregaEvent;
import org.freedom.acao.CarregaListener;
import org.freedom.infra.model.jdbc.DbConnection;
import org.freedom.library.functions.Funcoes;
import org.freedom.library.persistence.GuardaCampo;
import org.freedom.library.persistence.ListaCampos;
import org.freedom.library.swing.component.JLabelPad;
import org.freedom.library.swing.component.JPanelPad;
import org.freedom.library.swing.component.JTabbedPanePad;
import org.freedom.library.swing.component.JTablePad;
import org.freedom.library.swing.component.JTextFieldFK;
import org.freedom.library.swing.component.JTextFieldPad;
import org.freedom.library.swing.dialog.FFDialogo;
import org.freedom.library.swing.frame.Aplicativo;
import org.freedom.modulos.fnc.view.frame.crud.detail.FCheque;

public class DLEditaPag extends FFDialogo implements CarregaListener {

	private static final long serialVersionUID = 1L;

	private final JTextFieldPad txtCodFor = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 10, 0 );

	private final JTextFieldPad txtRazFor = new JTextFieldPad( JTextFieldPad.TP_STRING, 40, 0 );

	private final JTextFieldPad txtCodConta = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private final JTextFieldPad txtDescConta = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private final JTextFieldPad txtCodPlan = new JTextFieldPad( JTextFieldPad.TP_STRING, 13, 0 );

	private final JTextFieldPad txtDescPlan = new JTextFieldPad( JTextFieldPad.TP_STRING, 50, 0 );

	private final JTextFieldPad txtCodCC = new JTextFieldPad( JTextFieldPad.TP_STRING, 19, 0 );

	private final JTextFieldPad txtAnoCC = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 4, 0 );

	private final JTextFieldFK txtSiglaCC = new JTextFieldFK( JTextFieldPad.TP_STRING, 10, 0 );

	private final JTextFieldFK txtDescCC = new JTextFieldFK( JTextFieldPad.TP_STRING, 50, 0 );

	private final JTextFieldPad txtDoc = new JTextFieldPad( JTextFieldPad.TP_STRING, 10, 0 );

	private final JTextFieldPad txtDtEmis = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private final JTextFieldPad txtDtVenc = new JTextFieldPad( JTextFieldPad.TP_DATE, 10, 0 );

	private final JTextFieldPad txtVlrParc = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 2 );

	private final JTextFieldPad txtVlrJuros = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 2 );

	private final JTextFieldPad txtVlrDesc = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 2 );

	private final JTextFieldPad txtVlrDev = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, Aplicativo.casasDecFin );

	private final JTextFieldPad txtVlrAdic = new JTextFieldPad( JTextFieldPad.TP_NUMERIC, 15, 2 );

	private final JTextFieldPad txtObs = new JTextFieldPad( JTextFieldPad.TP_STRING, 250, 0 );

	private final JTextFieldPad txtCodTipoCob = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private final JTextFieldFK txtDescTipoCob = new JTextFieldFK( JTextFieldPad.TP_STRING, 40, 0 );

	private final ListaCampos lcConta = new ListaCampos( this );

	private final ListaCampos lcPlan = new ListaCampos( this );

	private final ListaCampos lcCC = new ListaCampos( this );

	private final ListaCampos lcTipoCob = new ListaCampos( this, "TC" );

	private JTabbedPanePad tpn = new JTabbedPanePad();

	private JPanelPad pnGeral = new JPanelPad();

	private JPanelPad pnCheques = new JPanelPad( new BorderLayout() );

	private JTablePad tabCheques = new JTablePad();

	private JScrollPane spnCheques = new JScrollPane( tabCheques );

	private JTextFieldPad txtCodPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private JTextFieldPad txtNParcPag = new JTextFieldPad( JTextFieldPad.TP_INTEGER, 8, 0 );

	private enum enum_grid_cheques {
		SEQCHEQ, NUMCHEQ, DTEMITCHEQ, DTVENCTOCHEQ, VLRCHEQ, SITCHEQ
	};

	public DLEditaPag( Component cOrig, boolean edita ) {

		super( cOrig );

		setTitulo( "Edi��o de contas a pagar" );
		setAtribos( 368, 445 );

		montaListaCampos();
		montaTela();
		montaGridCheques();

	}

	private void montaListaCampos() {

		lcConta.add( new GuardaCampo( txtCodConta, "NumConta", "N� Conta", ListaCampos.DB_PK, false ) );
		lcConta.add( new GuardaCampo( txtDescConta, "DescConta", "Descri��o da conta", ListaCampos.DB_SI, false ) );
		lcConta.montaSql( false, "CONTA", "FN" );
		lcConta.setReadOnly( true );
		txtCodConta.setTabelaExterna( lcConta, null );
		txtCodConta.setFK( true );
		txtCodConta.setNomeCampo( "NumConta" );

		lcPlan.add( new GuardaCampo( txtCodPlan, "CodPlan", "C�d.plan.", ListaCampos.DB_PK, false ) );
		lcPlan.add( new GuardaCampo( txtDescPlan, "DescPlan", "Descri��o do planejamento", ListaCampos.DB_SI, false ) );
		lcPlan.setWhereAdic( "TIPOPLAN = 'D' AND NIVELPLAN = 6" );
		lcPlan.montaSql( false, "PLANEJAMENTO", "FN" );
		lcPlan.setReadOnly( true );
		txtCodPlan.setTabelaExterna( lcPlan, null );
		txtCodPlan.setFK( true );
		txtCodPlan.setNomeCampo( "CodPlan" );

		lcCC.add( new GuardaCampo( txtCodCC, "CodCC", "C�d.c.c.", ListaCampos.DB_PK, false ) );
		lcCC.add( new GuardaCampo( txtSiglaCC, "SiglaCC", "Sigla", ListaCampos.DB_SI, false ) );
		lcCC.add( new GuardaCampo( txtDescCC, "DescCC", "Descri��o", ListaCampos.DB_SI, false ) );
		lcCC.add( new GuardaCampo( txtAnoCC, "AnoCC", "Ano-Base", ListaCampos.DB_PK, false ) );
		lcCC.setReadOnly( true );
		lcCC.setQueryCommit( false );
		lcCC.setWhereAdic( "NIVELCC=10" );
		lcCC.montaSql( false, "CC", "FN" );
		txtCodCC.setTabelaExterna( lcCC, null );
		txtCodCC.setFK( true );
		txtCodCC.setNomeCampo( "CodCC" );
		txtAnoCC.setTabelaExterna( lcCC, null );
		txtAnoCC.setFK( true );
		txtAnoCC.setNomeCampo( "AnoCC" );

		lcCC.addCarregaListener( this );

		txtCodTipoCob.setNomeCampo( "CodTipoCob" );
		lcTipoCob.add( new GuardaCampo( txtCodTipoCob, "CodTipoCob", "C�d.tp.cob.", ListaCampos.DB_PK, false ) );
		lcTipoCob.add( new GuardaCampo( txtDescTipoCob, "DescTipoCob", "Descri��o do tipo de cobran�a.", ListaCampos.DB_SI, false ) );
		lcTipoCob.montaSql( false, "TIPOCOB", "FN" );
		lcTipoCob.setQueryCommit( false );
		lcTipoCob.setReadOnly( true );
		txtCodTipoCob.setTabelaExterna( lcTipoCob, null );
		txtCodTipoCob.setListaCampos( lcTipoCob );
		txtDescTipoCob.setListaCampos( lcTipoCob );
		txtCodTipoCob.setFK( true );

	}

	private void montaTela() {

		txtCodFor.setAtivo( false );
		txtRazFor.setAtivo( false );
		txtDescConta.setAtivo( false );
		txtDescPlan.setAtivo( false );
		txtDtEmis.setAtivo( false );

		Container c = getContentPane();
		c.setLayout( new BorderLayout() );
		c.add( pnBordRodape, BorderLayout.SOUTH );
		c.add( tpn, BorderLayout.CENTER );

		tpn.addTab( "Informa��es gerais", pnGeral );

		// ABA GERAL

		pnGeral.adic( new JLabelPad( "C�d.for." ), 7, 0, 250, 20 );

		pnGeral.adic( txtCodFor, 7, 20, 80, 20 );
		pnGeral.adic( new JLabelPad( "Raz�o social do fornecedor" ), 90, 0, 250, 20 );
		pnGeral.adic( txtRazFor, 90, 20, 250, 20 );
		pnGeral.adic( new JLabelPad( "N� conta" ), 7, 40, 250, 20 );
		pnGeral.adic( txtCodConta, 7, 60, 80, 20 );
		pnGeral.adic( new JLabelPad( "Descri��o da conta" ), 90, 40, 250, 20 );
		pnGeral.adic( txtDescConta, 90, 60, 250, 20 );
		pnGeral.adic( new JLabelPad( "C�d.catg." ), 7, 80, 250, 20 );
		pnGeral.adic( txtCodPlan, 7, 100, 100, 20 );
		pnGeral.adic( new JLabelPad( "Descri��o da categoria" ), 110, 80, 250, 20 );
		pnGeral.adic( txtDescPlan, 110, 100, 230, 20 );
		pnGeral.adic( new JLabelPad( "C�d.c.c." ), 7, 120, 250, 20 );
		pnGeral.adic( txtCodCC, 7, 140, 100, 20 );
		pnGeral.adic( new JLabelPad( "Descri��o do centro de custo" ), 110, 120, 250, 20 );
		pnGeral.adic( txtDescCC, 110, 140, 230, 20 );
		pnGeral.adic( new JLabelPad( "Cod.Tp.Cob" ), 7, 160, 80, 20 );
		pnGeral.adic( txtCodTipoCob, 7, 180, 80, 20 );
		pnGeral.adic( new JLabelPad( "Descri��o do tipo de cobran�a" ), 90, 160, 250, 20 );
		pnGeral.adic( txtDescTipoCob, 90, 180, 250, 20 );

		pnGeral.adic( new JLabelPad( "Doc." ), 7, 200, 81, 20 );
		pnGeral.adic( txtDoc, 7, 220, 81, 20 );
		pnGeral.adic( new JLabelPad( "Emiss�o" ), 91, 200, 81, 20 );
		pnGeral.adic( txtDtEmis, 91, 220, 81, 20 );
		pnGeral.adic( new JLabelPad( "Vencimento" ), 175, 200, 81, 20 );
		pnGeral.adic( txtDtVenc, 175, 220, 81, 20 );
		pnGeral.adic( new JLabelPad( "Vlr.parcela" ), 259, 200, 81, 20 );
		pnGeral.adic( txtVlrParc, 259, 220, 81, 20 );

		pnGeral.adic( new JLabelPad( "Vlr.desc." ), 7, 240, 81, 20 );
		pnGeral.adic( txtVlrDesc, 7, 260, 81, 20 );
		pnGeral.adic( new JLabelPad( "Vlr.juros." ), 91, 240, 81, 20 );
		pnGeral.adic( txtVlrJuros, 91, 260, 81, 20 );
		pnGeral.adic( new JLabelPad( "Vlr.devolu��o" ), 175, 240, 81, 20 );
		pnGeral.adic( txtVlrDev, 175, 260, 81, 20 );
		pnGeral.adic( new JLabelPad( "Vlr.adicional" ), 259, 240, 81, 20 );
		pnGeral.adic( txtVlrAdic, 259, 260, 81, 20 );

		pnGeral.adic( new JLabelPad( "Observa��es" ), 7, 280, 200, 20 );
		pnGeral.adic( txtObs, 7, 300, 333, 20 );

		// ABA CHEQUES

//		tpn.addTab( "Cheques", pnCheques );

		pnCheques.add( spnCheques, BorderLayout.CENTER );

	}

	public void setValores( String[] sVals, boolean bLancaUsu ) {

		txtCodFor.setVlrString( sVals[ 0 ] );
		txtRazFor.setVlrString( sVals[ 1 ] );
		txtCodConta.setVlrString( sVals[ 2 ] );
		txtCodPlan.setVlrString( sVals[ 3 ] );
		txtCodCC.setVlrString( sVals[ 4 ] );
		txtDoc.setVlrString( sVals[ 5 ] );
		txtDtEmis.setVlrString( sVals[ 6 ] );
		txtDtVenc.setVlrString( sVals[ 7 ] );
		txtVlrParc.setVlrString( sVals[ 8 ] );
		txtVlrJuros.setVlrString( sVals[ 9 ] );
		txtVlrDesc.setVlrString( sVals[ 10 ] );
		txtVlrAdic.setVlrString( sVals[ 11 ] );
		txtObs.setVlrString( sVals[ 12 ] );
		txtCodTipoCob.setVlrString( sVals[ 13 ] );
		txtVlrDev.setVlrString( sVals[ 14 ] );

		txtCodPag.setVlrString( sVals[ 15 ] );
		txtNParcPag.setVlrString( sVals[ 16 ] );

		txtVlrParc.setAtivo( bLancaUsu );
	}

	public String[] getValores() {

		String[] sRetorno = new String[ 12 ];
		sRetorno[ 0 ] = txtCodConta.getVlrString();
		sRetorno[ 1 ] = txtCodPlan.getVlrString();
		sRetorno[ 2 ] = txtCodCC.getVlrString();
		sRetorno[ 3 ] = txtDoc.getVlrString();
		sRetorno[ 4 ] = txtVlrParc.getVlrString();
		sRetorno[ 5 ] = txtVlrJuros.getVlrString();
		sRetorno[ 6 ] = txtVlrAdic.getVlrString();
		sRetorno[ 7 ] = txtVlrDesc.getVlrString();
		sRetorno[ 8 ] = txtDtVenc.getVlrString();
		sRetorno[ 9 ] = txtObs.getVlrString();
		sRetorno[ 10 ] = txtCodTipoCob.getVlrString();
		sRetorno[ 11 ] = txtVlrDev.getVlrString();
		return sRetorno;
	}

	public void actionPerformed( ActionEvent evt ) {

		if ( evt.getSource() == btOK && txtDtVenc.getVlrString().length() < 10 ) {
			Funcoes.mensagemInforma( this, "Data do vencimento � requerido!" );
		}
		else {
			super.actionPerformed( evt );
		}
	}

	private int buscaAnoBaseCC() {

		int iRet = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = con.prepareStatement( "SELECT ANOCENTROCUSTO FROM SGPREFERE1 WHERE CODEMP=? AND CODFILIAL=?" );
			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "SGPREFERE1" ) );

			rs = ps.executeQuery();

			if ( rs.next() ) {
				iRet = rs.getInt( "ANOCENTROCUSTO" );
			}

			rs.close();
			ps.close();

			con.commit();
		} catch ( SQLException err ) {
			err.printStackTrace();
			Funcoes.mensagemErro( this, "Erro ao buscar lista de cheques\n" + err.getMessage(), true, con, err );
		}

		return iRet;
	}

	private int carregaCheques() {

		int iRet = 0;

		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();

		try {

			sql.append( "select ch.vlrcheq, ch.numcheq, ch.sitcheq, ch.seqcheq, pc.codpag, ch.dtemitcheq, ch.dtvenctocheq from fnpagcheq pc, fncheque ch " );
			sql.append( "where ch.codemp=pc.codempch and ch.codfilial=pc.codfilialch and ch.seqcheq=pc.seqcheq " );
			sql.append( "and pc.codemp=? and pc.codfilial=? and pc.codpag=? and pc.nparcpag=?" );

			ps = con.prepareStatement( sql.toString() );

			ps.setInt( 1, Aplicativo.iCodEmp );
			ps.setInt( 2, ListaCampos.getMasterFilial( "FNITRECEBER" ) );
			ps.setInt( 3, txtCodPag.getVlrInteger() );
			ps.setInt( 4, txtNParcPag.getVlrInteger() );

			rs = ps.executeQuery();

			tabCheques.limpa();

			for ( int i = 0; rs.next(); i++ ) {

				tabCheques.adicLinha();

				tabCheques.setValor( rs.getInt( enum_grid_cheques.SEQCHEQ.name() ), i, enum_grid_cheques.SEQCHEQ.ordinal() );
				tabCheques.setValor( rs.getInt( enum_grid_cheques.NUMCHEQ.name() ), i, enum_grid_cheques.NUMCHEQ.ordinal() );
				tabCheques.setValor( rs.getDate( enum_grid_cheques.DTEMITCHEQ.name() ), i, enum_grid_cheques.DTEMITCHEQ.ordinal() );
				tabCheques.setValor( rs.getDate( enum_grid_cheques.DTVENCTOCHEQ.name() ), i, enum_grid_cheques.DTVENCTOCHEQ.ordinal() );
				tabCheques.setValor( Funcoes.bdToStr( rs.getBigDecimal( enum_grid_cheques.VLRCHEQ.name() ), Aplicativo.casasDecFin ), i, enum_grid_cheques.VLRCHEQ.ordinal() );
				tabCheques.setValor( rs.getString( enum_grid_cheques.SITCHEQ.name() ), i, enum_grid_cheques.SITCHEQ.ordinal() );

			}
			
			tpn.remove(pnCheques);
			
			if(tabCheques.getNumLinhas()>0) {
				tpn.addTab( "Cheques", pnCheques );
			}

			rs.close();
			ps.close();

			con.commit();
		} catch ( SQLException err ) {
			err.printStackTrace();
			Funcoes.mensagemErro( this, "Erro ao buscar o ano-base para o centro de custo.\n" + err.getMessage(), true, con, err );
		}

		return iRet;
	}

	private void montaGridCheques() {

		tabCheques.addMouseListener( new MouseAdapter() {

			public void mouseClicked( MouseEvent mevt ) {

				if ( mevt.getSource() == tabCheques && mevt.getClickCount() == 2 )
					abreCheque();
			}
		} );

		tabCheques.adicColuna( "Seq." );
		tabCheques.adicColuna( "N�mero" );
		tabCheques.adicColuna( "Dt.Emiss�o" );
		tabCheques.adicColuna( "Dt.Vencto." );
		tabCheques.adicColuna( "Valor" );
		tabCheques.adicColuna( "Sit." );

		tabCheques.setColunaInvisivel( enum_grid_cheques.SEQCHEQ.ordinal() );
		tabCheques.setTamColuna( 60, enum_grid_cheques.NUMCHEQ.ordinal() );
		tabCheques.setTamColuna( 80, enum_grid_cheques.DTEMITCHEQ.ordinal() );
		tabCheques.setTamColuna( 80, enum_grid_cheques.DTVENCTOCHEQ.ordinal() );
		tabCheques.setTamColuna( 80, enum_grid_cheques.VLRCHEQ.ordinal() );
		tabCheques.setTamColuna( 20, enum_grid_cheques.SITCHEQ.ordinal() );

	}

	private void abreCheque() {

		if ( tabCheques.getLinhaSel() > -1 ) {
			FCheque tela = null;
			if ( Aplicativo.telaPrincipal.temTela( FCheque.class.getName() ) ) {
				tela = (FCheque) Aplicativo.telaPrincipal.getTela( FCheque.class.getName() );
			}
			else {
				tela = new FCheque();
				Aplicativo.telaPrincipal.criatela( "Cheque", tela, con );
			}
			tela.exec( (Integer) tabCheques.getValor( tabCheques.getLinhaSel(), enum_grid_cheques.SEQCHEQ.ordinal() ) );
		}
	
	}

	public void beforeCarrega( CarregaEvent cevt ) {

		if ( cevt.getListaCampos() == lcCC && txtAnoCC.getVlrInteger().intValue() == 0 ) {
			txtAnoCC.setVlrInteger( new Integer( buscaAnoBaseCC() ) );
		}
	}

	public void afterCarrega( CarregaEvent cevt ) {

	}

	public void setConexao( DbConnection cn ) {

		super.setConexao( cn );
		lcConta.setConexao( cn );
		lcConta.carregaDados();
		lcPlan.setConexao( cn );
		lcPlan.carregaDados();
		lcTipoCob.setConexao( cn );
		lcTipoCob.carregaDados();
		lcCC.setConexao( cn );
		lcCC.carregaDados();
		carregaCheques();
	}
}