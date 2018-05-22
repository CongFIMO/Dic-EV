package Dictionary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Gui extends JFrame implements ActionListener, KeyListener {

	private JTextArea tx;
	int n = 0;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JTextField Nhap;
	//private JPanel p4;
	private JButton btDich, btAnhViet, btVietAnh, btEdt;

	public Gui()  {
		setSize(900, 700);
		setTitle("English-Vietnamese Dictionary");
		setLocationRelativeTo(null);
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		CreatGUI();
	}

	private void CreatGUI() {
		JPanel main = new JPanel();
		DictEV.EVon();
		DictVE.VEon();
		main.setBackground(Color.cyan);

		// tao panel nhap
		JPanel p1 = new JPanel();
		JLabel lbNhap = new JLabel("Type a word:");
		Nhap = new JTextField(15);
		btDich = new JButton("Translate");
		btEdt = new JButton("Edit");
		p1.setBorder(BorderFactory.createTitledBorder("Search words"));
		p1.add(lbNhap, BorderLayout.SOUTH);
		p1.add(Nhap, BorderLayout.SOUTH);
		p1.add(btDich, BorderLayout.SOUTH);
		p1.add(btEdt, BorderLayout.SOUTH);
		p1.setPreferredSize(new Dimension(500, 100));
		p1.setBackground(Color.WHITE);
		Nhap.addKeyListener(this);
		btDich.addActionListener(this);
		btEdt.addActionListener(this);

		// tao panel tuy chon
		JPanel p2 = new JPanel();
		btAnhViet = new JButton("ENG-VIET");
		btVietAnh = new JButton("VIET-ENG");
		p2.setBorder(BorderFactory.createTitledBorder("Option"));
		p2.add(btAnhViet,  BorderLayout.SOUTH);
		p2.add(btVietAnh, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(300, 80));
		p2.setBackground(Color.lightGray);

		btAnhViet.addActionListener(this);
		btVietAnh.addActionListener(this);
		main.add(p2);
		main.add(p1);

		// danh sach tu
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.setBorder(BorderFactory.createTitledBorder("List"));
		p3.setPreferredSize(new Dimension(300, 550));
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setBorder(BorderFactory.createTitledBorder(""));
		p3.add(new JScrollPane(list), BorderLayout.CENTER);

		// nghia cua tu
		JPanel p4 = new JPanel(new BorderLayout());
		tx = new JTextArea(44, 33);
		p4.add(tx);
		p4.setBorder(BorderFactory.createTitledBorder("Meaning:"));
		p4.setPreferredSize(new Dimension(400, 550));
		p4.setBackground(Color.WHITE);

		main.add(p3);
		main.add(p4);

		
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList source = (JList) e.getSource();
				String word = (String) source.getSelectedValue();
				if (n == 0) {
					String result = DictEV.MapEV.get(word);
					if (result != null) {
						String[] kq = result.split("#");
						tx.setText(kq[0] + "\n\t" + kq[1] + "\n\t" + kq[2]);
						Nhap.setText(word);
					}
				}
				if (n == 1) {
					String result = DictVE.MapVE.get(word);
					if (result != null) {
						String[] kq = result.split("#");
						tx.setText(kq[0]);
						Nhap.setText(word);
					}
				}
			}
		});
		tx.setEditable(true);
		this.add(main);
	}

	private void ReloadJListEV() {
		// TODO Auto-generated method stub
		listModel.clear();
		for (String i : DictEV.word) {
			listModel.addElement(i);
		}
	}

	private void ReloadJListVE() {
		listModel.clear();
		for (String i : DictVE.word) {
			listModel.addElement(i);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto+-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (n == 0) {
			String txt = Nhap.getText();
			listModel.clear();
			for (int i = 0; i < DictEV.word.size(); i++) {
				if (DictEV.word.get(i).startsWith(txt)) {
					listModel.addElement(DictEV.word.get(i));
				}
			}
		}
		if (n == 1) {
			String txt = Nhap.getText();
			listModel.clear();
			for (int i = 0; i < DictVE.word.size(); i++) {
				if (DictVE.word.get(i).startsWith(txt)) {
					listModel.addElement(DictVE.word.get(i));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btAnhViet)) {
			n = 0;
			ReloadJListEV();
		}
		if (o.equals(btVietAnh)) {
			n = 1;
			ReloadJListVE();
		}

		// dich anh sang viet
		if (o.equals(btDich) && n == 0) {
			String result = DictEV.MapEV.get(Nhap.getText());
			if (result != null) {
				String[] kq = result.split("#");
				tx.setText(kq[0] + "\n\t" + kq[1] + "\n\t" + kq[2]);
			} else {
				tx.setText("NOT FOUND!!");
			}
		}

		// dich viet sag anh
		if (o.equals(btDich) && n == 1) {
			String result1 = DictVE.MapVE.get(Nhap.getText());
			if (result1 != null) {
				String[] kq = result1.split("#");
				tx.setText(kq[0]);
			} else {
				tx.setText("NOT FOUND!!");
			}
		}
		if(o.equals(btEdt) && n==1){
			if(Nhap.getText().equals("")){
				tx.setText("Nothing to do!!");
			}
			else{
				if(DictVE.MapVE.containsKey(Nhap.getText())==true){
					String key = Nhap.getText();
					DictVE.MapVE.remove(Nhap.getText());
					String value = tx.getText();
					DictVE.MapVE.put(key, value);
				}
				else{
					tx.setText("Word not found!! Add word first!");
				}
			}
		}
		
	}

}
