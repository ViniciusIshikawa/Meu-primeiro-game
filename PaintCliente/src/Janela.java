 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.net.*;



import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto     = new JButton ("Ponto"),
                      btnLinha     = new JButton ("Linha"),
                      btnCirculo   = new JButton ("Circulo"),
                      btnElipse    = new JButton ("Elipse"),
                      btnCorFora   = new JButton ("Fora"),
                      btnCorDentro = new JButton ("Dentro"),
                      btnAbrir     = new JButton ("Abrir"),
                      btnSalvar    = new JButton ("Salvar"),
                      btnQuadrado  = new JButton ("Quadrado"),
                      btnRetangulo  = new JButton ("Retangulo");
    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    
    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:"),
                     imagens    = new JLabel (""); 
    
                     

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta,esperaCirculo,esperaElipse,
                      esperaCorFora,esperaCorDentro,esperaQuadrado,esperaRetangulo,esperaAbrir,esperaSalvar;

    protected Color corFora = Color.BLACK;
    protected Ponto p1;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gráfico");

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.png"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.png"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorForaImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorFora.setIcon(new ImageIcon(btnCorForaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCorDentroImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCorDentro.setIcon(new ImageIcon(btnCorDentroImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnCorFora.addActionListener(new CorDeFora());
        btnCorDentro.addActionListener(new CorDeDentro());
        btnQuadrado.addActionListener(new DesenhoQuadrado());
        btnRetangulo.addActionListener(new DesenhoRetangulo());
        btnAbrir.addActionListener(new Abrir());
        btnSalvar.addActionListener(new Salvar());



        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnCorFora);
        pnlBotoes.add (btnCorDentro);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);
        pnlStatus.add(imagens);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (800,600);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
	public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e)
        {
            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corFora));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            }
            else
                if (esperaInicioReta)
                {
                    p1 = new Ponto (e.getX(), e.getY(), corFora);
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Mensagem: clique o ponto final da reta");    
                 }
                 else
                    if (esperaFimReta)
                    {
                        esperaInicioReta = false;
                        esperaFimReta = false;
                        figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corFora));
                        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        statusBar1.setText("Mensagem:");    
                    }
                    else
                    	if(esperaCirculo)
                    	{          		
                    		figuras.add(new Circulo (e.getX(),e.getY()));
                    		figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                    		esperaCirculo = false;
                    	}
                    	else
                    		if (esperaElipse)
                    		{
                        		figuras.add(new Elipse (e.getX(),e.getY()));
                        		figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                        		esperaElipse = false;
                    		}
                    		else 
                    			if (esperaCorFora)
                    			{        
                    				
                     	          Color cor = JColorChooser.showDialog(this, "Escolha uma cor!!!",Color.BLUE );
                     	         figuras.get(figuras.size()-1).setCor(cor);
                     	        figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                     	        esperaCorFora = false;
                     	         
                    			}
                    			else
                    				if (esperaCorDentro)
                    			{
                    				Color cor = JColorChooser.showDialog(this, "Escolha uma cor!!!",Color.BLUE );
                    				Figura f = figuras.get(figuras.size()-1);
                    				Circulo circulo = new Circulo(e.getX(),e.getY(),cor);
                    				Elipse elipse   = new Elipse(e.getX(),e.getY(),cor);
                    				
                    				if (f.getClass() == Circulo.class)
                    				circulo.CirculoPintado(pnlDesenho.getGraphics());
                    				
                    				if (f.getClass() == Elipse.class)
                    					elipse.ElipsePintada(pnlDesenho.getGraphics());
                    				
                    			
                    				esperaCorDentro = false;
                    			}
            
                    				else
                        				if (esperaQuadrado)
                        			{
                                    	figuras.add(new Quadrado (e.getX(),e.getY()));
                                    	figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                        esperaQuadrado = false;
                        			}
            
                        				else
                            				if (esperaRetangulo)
                            			{
                                            figuras.add(new Retangulo (e.getX(),e.getY()));
                                            figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                                            esperaRetangulo = false;
                            			}
                            				else
                                				if (esperaAbrir)
                                			{
                                		       
                                				
                                				Janela novaImagem = new Janela();
                                				novaImagem.add(imagens);
                                				
                                				imagens.setBounds(0,0,pnlDesenho.getWidth(),pnlDesenho.getHeight());
                                                JFileChooser arquivo = new JFileChooser();
                                                arquivo.setDialogTitle("Escolha uma imagem");
                                                arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                                
                                                int opc = arquivo.showOpenDialog(arquivo);
                                                
                                                File file = new File("caminho");
                                                file = arquivo.getSelectedFile();
                                                String filename = file.getAbsolutePath();
                                                
                                                
                                                
                                                ImageIcon imagem = new ImageIcon(arquivo.getSelectedFile().getPath());
                                                imagens.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(pnlDesenho.getWidth(), pnlDesenho.getHeight(), Image.SCALE_DEFAULT)));
                                                	
                                                esperaAbrir = false;
                                                	
                                                
                                			}
                                				else
                                    				if (esperaSalvar)
                                    				{
                                    					final String HOST_PADRAO  = "localhost";
                                    					final int    PORTA_PADRAO = 3000;
                                    					
                                    					Socket conexao=null;
                                    			        try
                                    			        {
                                    			            String host = Cliente.HOST_PADRAO;
                                    			            int    porta= Cliente.PORTA_PADRAO;

                                    			            conexao = new Socket (host, porta);
                                    			        }
                                    			        catch (Exception erro)
                                    			        {
                                    			            System.err.println ("Indique o servidor e a porta corretos!\n");
                                    			            return;
                                    			        }

                                    			        ObjectOutputStream transmissor=null;
                                    			        try
                                    			        {
                                    			            transmissor =
                                    			            new ObjectOutputStream(
                                    			            conexao.getOutputStream());
                                    			        }
                                    			        catch (Exception erro)
                                    			        {
                                    			            System.err.println ("Indique o servidor e a porta corretos!\n");
                                    			            return;
                                    			        }

                                    			        ObjectInputStream receptor=null;
                                    			        try
                                    			        {
                                    			            receptor =
                                    			            new ObjectInputStream(
                                    			            conexao.getInputStream());
                                    			        }
                                    			        catch (Exception erro)
                                    			        {
                                    			            System.err.println ("Indique o servidor e a porta corretos!\n");
                                    			            return;
                                    			        }

                                    			        Parceiro servidor=null;
                                    			        try
                                    			        {
                                    			            servidor =
                                    			            new Parceiro (conexao, receptor, transmissor);
                                    			        }
                                    			        catch (Exception erro)
                                    			        {
                                    			            System.err.println ("Indique o servidor e a porta corretos!\n");
                                    			            return;
                                    			        }
                                    				}
                    	
        }
        
        public void mouseReleased (MouseEvent e)
        {}
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = true;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaQuadrado    = false;
              esperaRetangulo    = false;
              esperaCorFora    = false;
              esperaCorDentro  = false;

              statusBar1.setText("Mensagem: clique o local do ponto desejado");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = true;
            esperaFimReta    = false;
            esperaQuadrado    = false;
            esperaRetangulo    = false;
            esperaAbrir      = false;
            esperaCirculo    = false;
            esperaElipse     = false;
            esperaCorFora    = false;
            esperaCorDentro  = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
    
    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaQuadrado    = false;
            esperaRetangulo    = false;
            esperaFimReta    = false;
            esperaAbrir      = false;
            esperaCirculo    = true;
            esperaElipse     = false;
            esperaCorFora    = false;
            esperaCorDentro  = false;

            statusBar1.setText("Mensagem: clique para desenhar o circulo");
        }
    }
    
    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto      = false;
            esperaInicioReta = false;
            esperaFimReta    = false;
            esperaCirculo    = false;
            esperaQuadrado    = false;
            esperaAbrir      = false;
            esperaRetangulo    = false;
            esperaElipse     = true;
            esperaCorFora    = false;
            esperaCorDentro  = false;

            statusBar1.setText("Mensagem: clique para desenhar o circulo");
        }
    }
    
    protected class CorDeFora implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaQuadrado    = false;
              esperaRetangulo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaCorFora    = true;
              esperaCorDentro  = false;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }
    
    protected class CorDeDentro implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaQuadrado    = false;
              esperaRetangulo    = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaCorFora    = false;
              esperaCorDentro  = true;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }
    
    protected class DesenhoQuadrado implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaCorFora    = false;
              esperaAbrir      = false;
              esperaCorDentro  = false;
              esperaQuadrado    = true;
              esperaRetangulo    = false;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }
    
    protected class DesenhoRetangulo implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaCorFora    = false;
              esperaCorDentro  = false;
              esperaQuadrado    = false;
              esperaRetangulo    = true;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }
    
    protected class Abrir implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaCorFora    = false;
              esperaCorDentro  = false;
              esperaAbrir      = true;
              esperaQuadrado    = false;
              esperaRetangulo    = false;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }
    
    protected class Salvar implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto      = false;
              esperaInicioReta = false;
              esperaFimReta    = false;
              esperaCirculo    = false;
              esperaElipse     = false;
              esperaAbrir      = false;
              esperaCorFora    = false;
              esperaCorDentro  = false;
              esperaAbrir      = false;
              esperaQuadrado    = false;
              esperaRetangulo    = false;
              esperaSalvar     = true;

              statusBar1.setText("Mensagem: Escolha uma cor");
          }
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}
