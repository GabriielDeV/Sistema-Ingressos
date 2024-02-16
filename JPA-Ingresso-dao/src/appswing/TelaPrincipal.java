//package appswing;
///**********************************
// * IFPB - SI
// * POB - Persistencia de Objetos
// * Prof. Fausto Ayres
// **********************************/
//
//import java.awt.Cursor;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;
//
//import regras_negocio.Fachada;
//
//public class TelaPrincipal {
//	private JFrame frame;
//	private JMenu mnPessoa;
//	private JLabel label;
//	private JMenu mnPessoa_1;
//	private JMenuItem mntmCadastrarPessoa_1;
//	private JMenuItem mntmApagarPessoa_1;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaPrincipal window = new TelaPrincipal();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public TelaPrincipal() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//
//		frame.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//				frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				Fachada.inicializar();
//
//				frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//				label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
//				ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/viagem.jpg"));
//				imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
//				label.setIcon(imagem);
//			}
//			@Override
//			public void windowClosing(WindowEvent e) {
//				Fachada.finalizar();
//			}
//		});
//		frame.setTitle("Viagem");
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		frame.setResizable(false);
//
//		label = new JLabel("");
//		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setBounds(0, 0, 444, 249);
//		label.setText("Aguarde...");
//		frame.getContentPane().add(label);
//
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		mnPessoa = new JMenu("Vaga");
//		mnPessoa.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				TelaVaga tela = new TelaVaga();
//			}
//		});
//		menuBar.add(mnPessoa);
//
//		mnPessoa_1 = new JMenu("Banco");
//		menuBar.add(mnPessoa_1);
//
//		mntmCadastrarPessoa_1 = new JMenuItem("Criar 10 vagas");
//		mntmCadastrarPessoa_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Fachada.criarVagas(10);
//				}
//				catch(Exception e) {
//					JOptionPane.showMessageDialog(frame, e.getMessage());
//				}
//			}
//		});
//		mnPessoa_1.add(mntmCadastrarPessoa_1);
//
//		mntmApagarPessoa_1 = new JMenuItem("Apagar vagas");
//		mntmApagarPessoa_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					Fachada.apagarVagas();
//				}
//				catch(Exception e) {
//					JOptionPane.showMessageDialog(frame, e.getMessage());
//				}
//			}
//		});
//		mnPessoa_1.add(mntmApagarPessoa_1);
//
//	}
//}
