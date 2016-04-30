import java.util.Random;

public class Ataque {

	/*Vetores que cont�m todos os ataques poss�veis para um tipo definido e ataques padr�es (que valem para todos
	 * os tipos) para atribuir aleat�riamente ataques a um dado pokemon.
	 */
	public static String[] nomesPredefinidos_fogo = {"Nuvem de Brasas","Bola de Fogo","Lan�a Chamas"};
	public static String[] nomesPredefinidos_agua = {"Canh�o d'gua","Enxame de Bolhas","Chuva �cida"};
	public static String[] nomesPredefinidos_eletrico = {"Choque do Trov�o","Curto Circuito","Raios"};
	public static String[] nomesPredefinidos_grama = {"Folhas Navalha","Raizes Cortantes","N�vem de P�lem"};
	public static String[] nomesPredefinidos_rocha = {"Avanlanche de Lama","Chuva de Pedregulhos","Tempestade de Areia"};
	public static String[] nomesPredefinidos_padrao = {"Cravar Dentes","Soco Potente","Mega Voadora","Rugido S�nico",
														"Abra�o de Urso", "Giro da Morte", "Ataque de F�ria", "Olhar Hipn�tico"};
	private String nome;
	private double dano; 
	private int velocidade;
	
	//Atribui nomes aleat�rios para um dado ataque a ser criado.
	public Ataque(String tipo, int velocidade, double dano)
	{
		Random gerador = new Random();
		
		//Caso o tipo seja inserido errado ou o tipo seja padrao, cria um ataque padrao disponivel a todos os pokemons
				if(!tipo.equals("fogo") || !tipo.equals("�gua") || !tipo.equals("el�trico") || !tipo.equals("rocha") ||
				!tipo.equals("grama"))
					this.nome = Ataque.nomesPredefinidos_padrao[gerador.nextInt(8)];
		
		if(tipo.equals("fogo"))
			this.nome = Ataque.nomesPredefinidos_fogo[gerador.nextInt(3)];
		if(tipo.equals("agua"))
			this.nome = Ataque.nomesPredefinidos_agua[gerador.nextInt(3)];
		if(tipo.equals("el�trico"))
			this.nome = Ataque.nomesPredefinidos_eletrico[gerador.nextInt(3)];
		if(tipo.equals("grama"))
			this.nome = Ataque.nomesPredefinidos_grama[gerador.nextInt(3)];
		if(tipo.equals("rocha"))
			this.nome = Ataque.nomesPredefinidos_rocha[gerador.nextInt(3)];
		
		
		
		this.velocidade = velocidade;
		this.dano = dano;
	}
	
	public Ataque()
	{
		nome="a";
		dano=0; 
		velocidade=3;
	}
	
	public double getDano()
	{
		return dano;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public int getVelocidade()
	{
		return velocidade;
	}
}
