import java.util.Scanner;
import java.util.Random;

public class BatalhaControls extends Controller
{
	boolean troc = false;
	Pokemon selvagem_morto = new Pokemon();
	boolean matou_selvagem = false;
	class Atacar extends Event
	{
		Pokemon atacando = new Pokemon();
		Pokemon morto = new Pokemon();
		Treinador atacado = new Treinador();
		int ataq;
		long tm;
		boolean morreu = false;
		double dano;
		public Atacar (long eventTime, Treinador atacan, Treinador atacad,int ata)
		{
			super(eventTime);
			atacando=atacan.escolhido;
			atacado=atacad;
			ataq=ata;
			tm=eventTime;
		}
	
		
		public void action() 
		{
			Pokemon patacado = new Pokemon();
			boolean vivo;
			int qtdpoke;
			patacado = atacado.escolhido;
			Ataque at = new Ataque();
			at=atacando.atak(ataq);
			dano = atacando.calculaAtaque(at, patacado.getTipo());
			patacado.tomaDano(dano);
			vivo=patacado.estaVivo();
			if(vivo==false)
			{
				morreu=true;
				if(atacado.getTipo()==false)
				{
					selvagem_morto=patacado;
					matou_selvagem = true;
				}
				morto=patacado;
				atacado.remover_pokemon(patacado);
				qtdpoke=atacado.qtdPokemons();
				if(qtdpoke==0)
				{
					fug = true;
				}
				else
				{
					troc = true;
				}	
			}
		}
		
		public String description() 
		{
			Pokemon patacado = new Pokemon();
			patacado = atacado.escolhido;
			if(morreu)
				return "O ataque é realizado pelo pokemon "+atacando.getNome()+" e o pokemon "+morto.getNome()+" morre";
			else
				return "O ataque é realizado pelo pokemon "+atacando.getNome()+", dano de "+dano+" e restam "+patacado.getHp()+"de Hp no pokemon "+patacado.getNome()+"";
		}
	}
	
	public class Trocar extends Event
	{
		boolean tipo;
		Treinador t;
		int i;
		public Trocar (long eventTime,Treinador trei, int tro,boolean tip)
		{
			super(eventTime);
			t=trei;
			i=tro;
			tipo=tip;
		}
		
		public void action () 
		{
			
			if(tipo)
			{
				t.escolher(i);
			}
			else
			{
				if(troc)
				{
					t.escolher(0);
					troc=false;
				}
			}
		}

		public String description() 
		{
			return "trocou o pokemon";
		}
	}
	
	private class Item extends Event
	{
		Treinador t;
		boolean tem;
		public Item (long eventTime,Treinador tre)
		{
			super(eventTime);
			t=tre;
		}
		
		public void action() 
		{
			Pokemon curado;
			tem=t.usarItem();
			curado=t.escolhido;
			if(tem)
				curado.curaDano((curado.getHpMax())/5);
		}

		public String description() 
		{
			if(tem)
				return "Pokemon com 20% a mais de vida e treinador com 1 item de cura a menos";
			else
				return "Treinador sem item, pokemon não curado";
		}
	}
	
	public class Fugir extends Event
	{
		Treinador tr;
		//Tipo false é fuga tipo true é falta de pokemon
		public Fugir (long eventTime,Treinador t)
		{
			
			super(eventTime);
			tr = t;
		}
		
		public void action() 
		{
			fug=true;
		}

		public String description() 
		{
			return "Treinador: "+tr.getNome()+" fugiu";
		}
	}
	
	public static class InterfaceCombate
	{
			public static void imprimirAtributos(Treinador treinador)
			{
				System.out.println("Treinador: "+treinador.getNome()+" | Pokemons restantes: "+treinador.qtdPokemons()+" | Quantidade de Itens: "+treinador.getItens());
			}
			
			public static void imprimirOpcoes()
			{
				System.out.println("1- Atacar | 2-Usar Item | 3-Trocar Pokemon | 4-Fugir || Opção Escolhida: ");
			}
			
			/*Recebe duas opção do treinador 1 e do treinador 2 e pondera qual das duas acontece primeiro. Retorna 0 se uma das opções
			escolhidas for inválida */
			public static int decideOpcao(String opcao1, String opcao2)
			{
				int op1,op2;
				
				if((opcao1.equals("1") || opcao1.equals("2") || opcao1.equals("3") || opcao1.equals("4")) &&
				(opcao2.equals("1") || opcao2.equals("2") || opcao2.equals("3") || opcao2.equals("4")))
				{
					op1 = Integer.parseInt(opcao1);
					op2 = Integer.parseInt(opcao2);
					
					if(op1 > op2)
						return 1;
					else
					{
						if(op1 == op2)
							return 1;
						else
							return 2;
					}
				}
				return 0;
			}
			
			public static void escolheOpcao(Treinador treinador1,Treinador treinador2, String opcao, long tm, BatalhaControls bat)
			{
				Scanner scanIn1 = new Scanner(System.in);
				int resposta;
				if(opcao.equals("1"))
				{
					InterfaceCombate.imprimeAtaques(treinador1);
					resposta = Integer.parseInt(scanIn1.nextLine());
					
					//Action para Ataque
					bat.addEvent(bat.new Atacar (tm,treinador1,treinador2,resposta));
					if(bat.troc)
						bat.addEvent(bat.new Trocar(tm,treinador2,0,false));
				}
					
				if(opcao.equals("2"))
				{
					//Action para Item
					bat.addEvent(bat.new Item (tm,treinador1));
					
				}
				
				if(opcao.equals("3"))
				{
					imprimePokemons(treinador1);
					resposta = Integer.parseInt(scanIn1.nextLine());
					
					//Action para Trocar de Pokemon
					bat.addEvent(bat.new Trocar (tm,treinador1, resposta,true));
					
				}
				
				if(opcao.equals("4"))
				{
					//action Fugir
					bat.addEvent(bat.new Fugir (tm,treinador1));
				}
				
				if(opcao.equals("S"))
				{
					Random gerador = new Random();		
					//Action para Ataque
					bat.addEvent(bat.new Atacar (tm,treinador1,treinador2,gerador.nextInt(3)));
					if(bat.troc)
						bat.addEvent(bat.new Trocar(tm,treinador2,0,false));
					
				}
			}
			
			public static void imprimeAtaques(Treinador treinador)
			{
				System.out.println("Treinador: "+treinador.getNome()+" quer atacar usando Pokemon: "+treinador.escolhido.getNome()+"");
				System.out.println("0- "+treinador.escolhido.getAtaque(0)+" | 1- "+treinador.escolhido.getAtaque(1)+" | 2- "+treinador.escolhido.getAtaque(2));
			}
			
			public static void imprimePokemons(Treinador treinador)
			{
				System.out.println("Treinador "+treinador.getNome()+" quer trocar de pokemon, escolha entre: ");
				treinador.lista_pokemons();
			}
	
			public static void versus(Treinador t1)
			{
				String resposta1 ="1", resposta2 = "1";
				int primeiro = 0;
				Scanner scanIn = new Scanner(System.in);
				
				Treinador t2 = new Treinador ("Dias",true);
				t2.adicionar_um_item();
				t2.adicionar_um_item();
				t2.adicionar_um_item();
				
				long tm = System.currentTimeMillis();
				Pokemon p1 = new Pokemon("BatataSSauro","grama",1500);
				Pokemon p2 = new Pokemon("RosaSSauro","fogo",1000);
				Pokemon p3 = new Pokemon("Rocha o Gabriel","rocha",1200);
				Pokemon p4 = new Pokemon("BanhoQuente","água",900);
				
				t2.adcionar_pokemon(p1);
				t2.adcionar_pokemon(p2);
				t2.adcionar_pokemon(p3);
				t2.adcionar_pokemon(p4);
				
				t1.escolher(0);
				t2.escolher(0);
				BatalhaControls bat = new BatalhaControls();
				bat.addEvent(bat.new Trocar(tm+=1000,t1,0,true));
				bat.addEvent(bat.new Trocar(tm+=1000,t2,0,true));
				
				bat.run();
				
				while (!resposta1.equals("4") && !resposta2.equals("4"))
				{
					
					InterfaceCombate.imprimirAtributos(t1);
					InterfaceCombate.imprimirOpcoes();
					resposta1 = scanIn.nextLine();
					InterfaceCombate.imprimirAtributos(t2);
					InterfaceCombate.imprimirOpcoes();
					resposta2 = scanIn.nextLine();
					
					primeiro = InterfaceCombate.decideOpcao(resposta1,resposta2);
					
					if(primeiro == 1)
					{
						InterfaceCombate.escolheOpcao(t1,t2,resposta1,tm,bat);
						InterfaceCombate.escolheOpcao(t2,t1,resposta2,tm,bat);
					}
					
					if(primeiro == 2)
					{
						InterfaceCombate.escolheOpcao(t2,t1,resposta2,tm,bat);
						InterfaceCombate.escolheOpcao(t1,t2,resposta1,tm,bat);
					}
					
					if(primeiro == 0)
					{
						System.out.println("Um dos treinadores selecionou uma opção inválida\n");
					}
					else
						bat.run();
					if (bat.fug)
						break;
				}
				
				System.out.println("Termina a Batalha");
			}
	
			public static void selvagem(Treinador t1)
			{
				String resposta1 ="1", resposta2 = "1";
				int primeiro = 0;
				Scanner scanIn = new Scanner(System.in);
				Random gerador = new Random();
			
				Treinador t2 = new Treinador ("Pokemon Selvagem",false);
				long tm = System.currentTimeMillis();
				Pokemon p2 = new Pokemon("Selvagem","fogo",gerador.nextInt(1001)+500);
				
				t2.adcionar_pokemon(p2);
				t1.escolher(0);
				t2.escolher(0);
				
				BatalhaControls bat = new BatalhaControls();
				bat.addEvent(bat.new Trocar(tm+=1000,t1,0,true));
				bat.addEvent(bat.new Trocar(tm+=1000,t2,0,true));
				
				bat.run();
				
				while (!resposta1.equals("4") && !resposta2.equals("4"))
				{
					
					InterfaceCombate.imprimirAtributos(t1);
					InterfaceCombate.imprimirOpcoes();
					resposta1 = scanIn.nextLine();
					
					primeiro = InterfaceCombate.decideOpcao(resposta1,"1");
					
					if(primeiro == 1)
					{
						InterfaceCombate.escolheOpcao(t1,t2,resposta1,tm,bat);
						InterfaceCombate.escolheOpcao(t2,t1,"S",tm,bat);
					}
					
					if(primeiro == 2)
					{
						InterfaceCombate.escolheOpcao(t2,t1,"S",tm,bat);
						InterfaceCombate.escolheOpcao(t1,t2,resposta1,tm,bat);
					}
					
					if(primeiro == 0)
					{
						System.out.println("Um dos treinadores selecionou uma opção inválida\n");
					}
					else
						bat.run();
					if (bat.fug)
						break;
				}
				
				System.out.println("Termina a Batalha");
				if(bat.matou_selvagem)
				{
					bat.selvagem_morto.curaDano(bat.selvagem_morto.getHpMax());
					System.out.println("Pokemon Selvagem foi capturado");
					t1.adcionar_pokemon(bat.selvagem_morto);
					bat.matou_selvagem=false;
				}
				
			}
	}
	
	
	public static void main(String[]Args)
	{		
		Mapa mapa = new Mapa();
		mapa.imprimeMapa();
		
		String resposta = "X";
		Scanner scanIn1 = new Scanner(System.in);
		
		//Cria o treinador T1 original
		
		Treinador t1 = new Treinador ("Dobby",true);
		long tm = System.currentTimeMillis();
		
		Pokemon p1 = new Pokemon("Lutchador","grama",1500);
		Pokemon p2 = new Pokemon("DragonBorn","fogo",1000);
		Pokemon p3 = new Pokemon("GrabielRocha","rocha",1000);
		Pokemon p4 = new Pokemon("CurtoCircuitao","eletrico",1000);

		t1.adcionar_pokemon(p1);
		t1.adcionar_pokemon(p2);
		t1.adcionar_pokemon(p3);
		t1.adcionar_pokemon(p4);
		
		//Enquanto o jogador nao digitar para sair, roda o mapa do jogo.
		while(!resposta.equals("0"))
		{
			System.out.println("Digite a opcao: ");
			
			if(scanIn1.hasNextLine())
				resposta = scanIn1.nextLine();
			
			if(!resposta.equals("0"))
				mapa.move_jogador(resposta);
			mapa.imprimeMapa();
			
			if(mapa.batalha == 1)
			{
				System.out.println("----------------------------------------------------");
				System.out.println("\nIniciou o Modo Versus\n");
				System.out.println("----------------------------------------------------");
				InterfaceCombate.versus(t1);
				mapa.batalha = 0;
				mapa.jPosX--;
			}
			
			if(mapa.batalha == 2)
			{
				System.out.println("----------------------------------------------------");
				System.out.println("\nAchou um Pokemon Selvagem, iniciando batalha\n");
				System.out.println("----------------------------------------------------");
				InterfaceCombate.selvagem(t1);
				mapa.batalha = 0;
			}
			
			if(mapa.batalha == 3)
			{
				System.out.println("----------------------------------------------------");
				System.out.println("\nEncontrou um item\n");
				System.out.println("----------------------------------------------------");
				t1.adicionar_um_item();
				mapa.batalha = 0;
			}
			
			if(t1.qtdPokemons() == 0)
			{
				System.out.println("Treinador principal perdeu todos os pokemons, fim de jogo");
			}
			
		}	
		System.out.println("\nJogo Finalizado");
	}
}
