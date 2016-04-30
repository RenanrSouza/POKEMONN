import java.util.Scanner;

import java.util.Random;

public class Mapa {	
	
	//Matriz do mapa na qual "O" = limite do mapa, "I" = Item de cura, "T" = Treinador, "V" = modo versus, "W" = Grama , " " = Espaço comum para andar
	public String mapa[][];
	
	public int batalha = 0;
	
	//Posicões do Jogador no mapa, seta a posicao 1,10 como inicial
	public int jPosX = 1;
	public int jPosY = 10;
	
	//Posicões do modo versus no mapa
	public int PosVersusX = 8;
	public int PosVersusY = 10;
	
	//Posicões dos Itens e atributo que indica se já foram coletados no mapa ou não
	public int PosItem1X;
	public int PosItem1Y;
	public boolean item1usado;
	
	public int PosItem2X;
	public int PosItem2Y;
	public boolean item2usado;
	
	public int PosItem3X;
	public int PosItem3Y;
	public boolean item3usado;
	
	public int PosItem4X;
	public int PosItem4Y;
	public boolean item4usado;
	
	//Cria o mapa e seta todos os itens como não usados
	public Mapa()
	{
		mapa = new String[20][20];
		item1usado = false;
		item2usado = false;
		item3usado = false;
		item4usado= false;
		
		inicia_mapa();
		carregaBordas();
		carregaGrama();
		posicionaItens();
		posicionaVersus();
	}
	
	//Imprime todos os vazios que vão corresponder aos espaços para se andar que não estão na grama
	public void inicia_mapa()
	{
		int i = 0, j = 0;
		while(i < 10)
		{
			while(j < 20)
			{
				mapa[i][j] = " ";
				j++;
			}
			j = 0;
			i++;
			
		}
	}
	
	//Imprime os limites do mapa
	public void carregaBordas()
	{
		int i = 0;
		
		while(i < 10)
		{
			mapa[i][0] = "0";
			i++;
		}
		
		i = 0;
		
		while(i < 20)
		{
			mapa[0][i] = "0";
			i++;
		}
		
		i = 0;
		
		while(i < 20)
		{
			mapa[9][i] = "0";
			i++;
		}
		
		i = 0;
		
		while(i < 20)
		{
			mapa[i][19] = "0";
			i++;
		}

	}
	
	//Carrega as posições com grama no mapa
	public void carregaGrama()
	{
		int i = 1, j = 1;
		
		//Bloco de grama 1
		while(j < 8)
		{
			while(i < 4)
			{
				mapa[i][j] = "W";
				i++;
			}
			i=1;
			j++;
		}
		
		//Bloco de grama 2
		i = 1;
		j = 12;
		while(j < 19)
		{
			while(i < 4)
			{
				mapa[i][j] = "W";
				i++;
			}
			i=1;
			j++;
		}
		
		//Bloco de grama 3
		
		i = 6;
		j = 12;
		
		while(j < 19)
		{
			while(i < 9)
			{
				mapa[i][j] = "W";
				i++;
			}
			i=6;
			j++;
		}
		
		//Bloco de grama 4
		
				i = 6;
				j = 1;
				
				while(j < 8)
				{
					while(i < 9)
					{
						mapa[i][j] = "W";
						i++;
					}
					i=6;
					j++;
				}
	}
	
	//Seta a posicao de onde o modo versus se encontra no mapa, que inicia o versus quando treinador esta por cima
	public void posicionaVersus()
	{
		mapa[8][10] = "V";
	}
		
	//Posiciona Quatro itens de cura aleatóriamente pelo mapa
	public void posicionaItens()
	{
		Random gerador = new Random();
		
		PosItem1X = gerador.nextInt(8)+1;
		PosItem1Y = gerador.nextInt(18)+1;
		
		PosItem2X = gerador.nextInt(8)+1;
		PosItem2Y = gerador.nextInt(18)+1;
	
		PosItem3X = gerador.nextInt(8)+1;
		PosItem3Y = gerador.nextInt(18)+1;
		
		PosItem4X = gerador.nextInt(8)+1;
		PosItem4Y = gerador.nextInt(18)+1;
	}
	
	//Imprime os itens no mapa. Caso estejam usados imprime "" e caso não estejam imprime I
	public void imprimeItens()
	{
		if(item1usado == false)
			mapa[PosItem1X][PosItem1Y] = "I";
		else
			mapa[PosItem1X][PosItem1Y] = " ";
		
		if(item2usado == false)
			mapa[PosItem2X][PosItem2Y] = "I";
		else
			mapa[PosItem2X][PosItem2Y] = " ";
		
		if(item3usado == false)
			mapa[PosItem3X][PosItem3Y] = "I";
		else
			mapa[PosItem3X][PosItem3Y] = " ";
		
		if(item4usado == false)
			mapa[PosItem4X][PosItem4Y] = "I";
		else
			mapa[PosItem4X][PosItem4Y] = " ";
	}

	//Printa o mapa na tela
	public void imprimeMapa()
	{
		int i = 0, j = 0;
		String parametro[][] = new String[20][20];
		
		//Posiciona os itens no mapa
		imprimeItens();
		
		//Atribui mapa para parametro, criando um mapa parametro (para permitir a movimentação do treinado sem perder dados de quando ele está sobre objetos
		while(i < 10)
		{
			while(j < 20)
			{
				parametro[i][j] = mapa[i][j];
				j++;
			}
			
			j=0;
			i++;
		}
		
		//Coleta a posição do treinador no mapa
		i=j=0;
		parametro[jPosX][jPosY] = "T";
		
		//Printa o mapa com tudo carregado e a posicao do treinador já identificada
		while(i < 10)
		{
			while(j < 20)
			{
				System.out.print(parametro[i][j]);
				j++;
			}
			
			j=0;
			System.out.print("\n");
			i++;
		}
		
		
		imprimeOpcoes();
		imprimeStatusdoJogador();
	}
	
	//Imprime as opções de escolha para o jogador
	public void imprimeOpcoes()
	{
		System.out.println("\n\nOp��es 'w'-cima 's'-baixo 'a'-esquerda 'd'-direita '0' - sair ");
	}
	
	public void imprimeStatusdoJogador()
	{
		//Imprime a quantidade de pokemons e itens na mochila do treinador
	}
	
	public void move_jogador(String opcao)
	{
		//Indica para onde o treinador se move e checa para que ele não passe dos limites do mapa
		if(opcao.equals("a"))
		{
			if(jPosY - 1 > 0)
				jPosY = jPosY - 1;
		}
		
		if(opcao.equals("d"))
		{
			if(jPosY + 1 < 19)
				jPosY = jPosY + 1;
		}
		
		if(opcao.equals("w"))
		{
			if(jPosX - 1 > 0)
				jPosX = jPosX - 1;
		}
		
		if(opcao.equals("s"))
		{
			if(jPosX + 1 < 9)
				jPosX = jPosX + 1;
		}
		
		//Caso o jogador esteja sobre o V, carrega o modo versus
		if(jPosX == PosVersusX && jPosY == PosVersusY)
		{
			escolheBatalha(1);
		}
		

		//Caso o Treinador passe sobre os itens, coleta os itens e o adciona ao inventário do mesmo.
		
		if(jPosX == PosItem1X && jPosY == PosItem1Y)
		{
			item1usado = true;
			batalha = 3;
		}
		
		if(jPosX == PosItem2X && jPosY == PosItem2Y)
		{
			item2usado = true;
			batalha = 3;
		}
		if(jPosX == PosItem3X && jPosY == PosItem3Y)
		{
			item3usado = true;
			batalha = 3;
		}
		if(jPosX == PosItem4X && jPosY == PosItem4Y)
		{
			item4usado = true;
			batalha = 3;
		}
		
		//Caso o treinador esteja na grama, tem uma possibilidade aleatória de 1/6 encontrar um pokemon Selvagem
		if(mapa[jPosX][jPosY].equals("W"))
		{
			Random gerador = new Random();
			
			if(gerador.nextInt(6) == 5)
			{
				escolheBatalha(2);
			}	
		}

	}
	
	public void escolheBatalha(int i)
	{
		batalha = i;
	}
	
	public static void main(String[] args) {
	
		Mapa mapa = new Mapa();
		Scanner scanIn = new Scanner(System.in);
		mapa.imprimeMapa();
		
		String resposta = "X";
		
		//Enquanto o jogador nao digitar para sair, roda o mapa do jogo.
		while(!resposta.equals("0"))
		{
			System.out.println("Digite a opcao: ");
			resposta = scanIn.nextLine();
			
			if(!resposta.equals("0"))
				mapa.move_jogador(resposta);
			mapa.imprimeMapa();
		}
		
		System.out.println("\nJogo Finalizado");
	}
	

}