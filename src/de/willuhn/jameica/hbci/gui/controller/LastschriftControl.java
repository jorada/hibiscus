/**********************************************************************
 * $Source: /cvsroot/hibiscus/hibiscus/src/de/willuhn/jameica/hbci/gui/controller/LastschriftControl.java,v $
 * $Revision: 1.14 $
 * $Date: 2011/05/10 11:41:30 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.willuhn.jameica.hbci.gui.controller;

import java.rmi.RemoteException;

import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.hbci.TextSchluessel;
import de.willuhn.jameica.hbci.gui.action.LastschriftNew;
import de.willuhn.jameica.hbci.gui.parts.LastschriftList;
import de.willuhn.jameica.hbci.rmi.BaseUeberweisung;
import de.willuhn.jameica.hbci.rmi.HibiscusTransfer;
import de.willuhn.jameica.hbci.rmi.Lastschrift;
import de.willuhn.jameica.hbci.rmi.Terminable;

/**
 * Ueberschreiben wir von UeberweisungControl, weil es fast das
 * gleiche ist.
 */
public class LastschriftControl extends AbstractBaseUeberweisungControl
{

	private LastschriftList table      = null;
  private SelectInput textschluessel = null;
	
  /**
   * ct.
   * @param view
   */
  public LastschriftControl(AbstractView view)
  {
    super(view);
  }


  /**
   * @see de.willuhn.jameica.hbci.gui.controller.AbstractTransferControl#getTransfer()
   */
  public HibiscusTransfer getTransfer() throws RemoteException
  {
    return (Lastschrift) getCurrentObject();
  }

  /**
   * Liefert eine Liste existierender Lastschriften.
   * @return Liste der Lastschriften.
   * @throws RemoteException
   */
  public LastschriftList getLastschriftListe() throws RemoteException
  {
		if (table != null)
			return table;

    table = new de.willuhn.jameica.hbci.gui.parts.LastschriftList(new LastschriftNew());
		return table;
  }
  
  /**
   * @see de.willuhn.jameica.hbci.gui.controller.AbstractBaseUeberweisungControl#getTextSchluessel()
   */
  public Input getTextSchluessel() throws RemoteException
  {
    if (textschluessel != null)
      return textschluessel;

    textschluessel = new SelectInput(TextSchluessel.get(TextSchluessel.SET_LAST),TextSchluessel.get(((BaseUeberweisung)getTransfer()).getTextSchluessel()));
    textschluessel.setName(i18n.tr("Textschlüssel"));
    textschluessel.setEnabled(!((Terminable)getTransfer()).ausgefuehrt());
    return textschluessel;
  }
}
