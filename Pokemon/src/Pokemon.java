import java.util.Random;

public class Pokemon {

	private String nome;
	private String tipo;
	
	/*Vetor que contem todos os tipos de pokemons que podem ser criados para quando um pokemon for criado 
	aleatóriamente*/
	public static String todos_os_tipos[] = {"fogo", "água", "elétrico", "rocha", "grama"};
	
	private double hp_max;
	private double hp;
	private Ataque[] ataques = new Ataque[4];
	
	//Cria um pokemon com um tipo definido
	public Pokemon(String nome, String tipo, double hp_max)
	{
		this.nome = nome;
		this.hp = hp_max;
		this.hp_max = hp_max;
		this.tipo = tipo;
		Random gerador = new Random();
		
		//Ataque 1
		ataques[0] = new Ataque("padrao",3,gerador.nextInt(201)+100);
		
		/*Ataque 2 (Com entuito de randomizar ainda mais o nome dos ataques, podendo definir 2 ataques padrões
		ou dois ataques especiais do tipo para cada pokemon)*/
		if(gerador.nextInt(2) == 1)
			ataques[1] = new Ataque("padrao",2,gerador.nextInt(301)+150);
		else
			ataques[1] = new Ataque(tipo,2,gerador.nextInt(301)+150);
		
		//Ataque 3
		ataques[2] = new Ataque(tipo,2,gerador.nextInt(401)+200);
	}
	
	//Cria um pokemon com um tipo aleatório
	public Pokemon(String nome, double hp_max)
	{
		this.nome = nome;
		this.hp = hp_max;
		this.hp_max = hp_max;
		Random gerador = new Random();
		
		tipo = Pokemon.todos_os_tipos[gerador.nextInt(5)];
		
		//Ataque 1
		ataques[0] = new Ataque("padrao",3,gerador.nextInt(201)+100);
		
		/*Ataque 2 (Com entuito de randomizar ainda mais o nome dos ataques, podendo definir 2 ataques padrões
		ou dois ataques especiais do tipo para cada pokemon)*/
		if(gerador.nextInt(2) == 1)
			ataques[1] = new Ataque("padrao",2,gerador.nextInt(301)+150);
		else
			ataques[1] = new Ataque(tipo,2,gerador.nextInt(301)+150);
		
		//Ataque 3
		ataques[2] = new Ataque(tipo,2,gerador.nextInt(401)+200);
	}
	
	public Pokemon()
	{
		this.nome = "a";
		this.hp = 0;
		this.hp_max = 0;
		this.tipo = "b";
	}
	
	/*Calcula o ataque do pokemon com base no ataque escolhido, em que tipo de pokemon esse é e qual pokemon
	está sendo atacado*/
	public double calculaAtaque(Ataque ataq, String tipo_atacado)
	{
		double modificador_de_dano = 1;
		
		if(tipo.equals("fogo"))
		{
			if(tipo_atacado.equals("fogo"))
				modificador_de_dano = 2;
			if(tipo_atacado.equals("água"))
				modificador_de_dano = 0.5;
			if(tipo_atacado.equals("elétrico"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("rocha"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("grama"))
				modificador_de_dano = 2;			
		}
		
		if(tipo.equals("água"))
		{
			if(tipo_atacado.equals("fogo"))
				modificador_de_dano = 2;
			if(tipo_atacado.equals("água"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("elétrico"))
				modificador_de_dano = 0.5;
			if(tipo_atacado.equals("rocha"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("grama"))
				modificador_de_dano = 1;			
		}
		
		if(tipo.equals("elétrico"))
		{
			if(tipo_atacado.equals("fogo"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("água"))
				modificador_de_dano = 2;
			if(tipo_atacado.equals("elétrico"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("rocha"))
				modificador_de_dano = 0.5;
			if(tipo_atacado.equals("grama"))
				modificador_de_dano = 1;			
		}
		
		if(tipo.equals("rocha"))
		{
			if(tipo_atacado.equals("fogo"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("água"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("elétrico"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("rocha"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("grama"))
				modificador_de_dano = 1;			
		}
		
		if(tipo.equals("grama"))
		{
			if(tipo_atacado.equals("fogo"))
				modificador_de_dano = 0.75;
			if(tipo_atacado.equals("água"))
				modificador_de_dano = 1.75;
			if(tipo_atacado.equals("elétrico"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("rocha"))
				modificador_de_dano = 1;
			if(tipo_atacado.equals("grama"))
				modificador_de_dano = 1;			
		}
		
		return ataq.getDano() * modificador_de_dano;
	}
	
	//Aumenta o hp de um pokemon até o valor de HP maximo definido quando o mesmo foi criado.
	public void curaDano(double valor)
	{
		if(valor + hp > hp_max)
			hp = hp_max;
		else
			hp = hp + valor;
	}
	
	//Diminui o hp de um pokemon até 0
	public void tomaDano(double valor)
	{
		if(hp - valor < 0)
			hp = 0;
		else
			hp = hp - valor;
	}
	
	//Verifica se o HP de um pokemon é maior que 0, indicando que está vivo se positivo ou derrotado caso contrario
	public boolean estaVivo()
	{
		if(hp>0)
			return true;
		else
			return false;
	}
	
	
	public String getTipo()
	{
		return tipo;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public double getHpMax()
	{
		return hp_max;
	}
	
	
	public double getHp()
	{
		return hp;
	}
	
	public Ataque atak(int n)
	{
		Ataque at = new Ataque();
		at=ataques[n];
		return at;
	}
	
	public String getAtaque(int i)
	{
		return ataques[i].getNome();
	}
}
