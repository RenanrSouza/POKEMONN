import java.util.Random;

public class Treinador {
	
	private String nome;
	private Pokemon[] pokemons = new Pokemon[6];
	private int qtd_de_pokemons;
	private int qtd_de_itens_cura;
	public Pokemon escolhido;
	private boolean é_jogador; /*Caso seja TRUE define que o treinador é um jogador, caso contrário se refere
	a um pokemon do tipo selvagem que é seu próprio treinador*/
	
	public Treinador(String nome, boolean é_jogador)
	{
		this.nome = nome;
		this.é_jogador = é_jogador;
		qtd_de_itens_cura = 0;
		qtd_de_pokemons = 0;
	}
	
	public Treinador()
	{
		nome="a";
		Pokemon[] pokemons = new Pokemon[6];
		qtd_de_pokemons = 0;
		qtd_de_itens_cura = 1;
		escolhido = new Pokemon();
		é_jogador = false;
	}
	
	//Cria um pokemon randomico para o treinador com um nome pre definido
	public boolean adcionar_pokemon_randomico(String nome)
	{
		if(qtd_de_pokemons < 6)
		{
			Random gerador = new Random();
			Pokemon p = new Pokemon(nome,(double)(1500+gerador.nextInt(1501)));
			pokemons[qtd_de_pokemons] = p;
			qtd_de_pokemons++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*Adiciona um pokemon a lista de pokemons do treinador. Retorna true caso isso seja possível e false caso o
	 * treinador já tenha 6 pokemons*/
	 
	public boolean adcionar_pokemon(Pokemon pokemon)
	{
		if(qtd_de_pokemons < 6)
		{
			pokemons[qtd_de_pokemons] = pokemon;
			qtd_de_pokemons++;
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	//Remove um pokemon da lista do jogador
	public void remover_pokemon(Pokemon pokemon)
	{
		Pokemon nova_lista_de_pokemons[]= new Pokemon[6];
		int i = 0;
		int j = 0;
		
		while(i < qtd_de_pokemons)
		{
			if(pokemons[i] != pokemon)
				nova_lista_de_pokemons[j++] = pokemons[i];
			i++;
		}
		
		pokemons = nova_lista_de_pokemons;
		qtd_de_pokemons--;
		
	}
	
	//Lista todos os pokemons que o treinador têm
	public void lista_pokemons()
	{
		int i = 0;
		
		if(qtd_de_pokemons > 0)
		{
			while(i < qtd_de_pokemons)
			{
				System.out.println((i)+"- Nome: "+pokemons[i].getNome()+" , Tipo: "+pokemons[i].getTipo());
				i++;
			}
		}
		
		else
		{
			System.out.println("Não têm pokemons");
		}
	}
	
	//Adiciona um item de cura a backpack do jogador
	public void adicionar_um_item()
	{
		qtd_de_itens_cura++;
	}
	
	public void escolher(int esco)
	{
		escolhido=pokemons[esco];
	}
	
	public boolean usarItem()
	{
		boolean tem;
		if(qtd_de_itens_cura==0)
			return false;
		qtd_de_itens_cura--;
		return true;
	}
	
	public int qtdPokemons()
	{
		return qtd_de_pokemons;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public int getItens()
	{
		return qtd_de_itens_cura;
	}
	
	public boolean getTipo()
	{
		return é_jogador;
	}
}
