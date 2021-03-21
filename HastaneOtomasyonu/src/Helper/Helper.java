package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz !";
			break;
		case "success":
			msg = "Ýþlem baþarýlý";
			break;
		default:
			msg = str;

		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();

		switch (str) {
		case "sure":
			msg = "Bu iþlemi gerçekleþtirmek istediðinizden emin misiniz ? ";
			break;
		default:
			msg = str;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		} else {
			return false;
		}
	}
}
