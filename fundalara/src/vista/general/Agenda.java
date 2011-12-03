package vista.general;

import org.zkoss.calendar.Calendars;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

public class Agenda extends GenericForwardComposer{
	Popup updateMsg;
	Label popupLabel;
	Timer timer;
	Calendars calendars;
	Span FDOW;
	Listbox filtro;
	Label label;	
	
}