package me.thiago.jfb;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

//Variaveis	
	
	public static Main jp;
	public static String nome_do_servidor;
	
//Servidor Online	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("§a[Java for Bukkit] Plugin Iniciado com sucesso! :3");
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		saveDefaultConfig();
		saveConfig();
		nome_do_servidor = getConfig().getString("Informacoes.Nome-do-servidor");
		jp = this;
	}
	
//Ultimo Jogador
	
	public static String getUltimoJogador() {
		
		return Main.jp.getConfig().getString("Ultimo-Jogador.Nome");
	}

//Vida e Comida Maximos	
	
	public static void setMaxHealtAndFood(Player p) {
		p.setFoodLevel(20);
		p.setHealth(20.0);
	}
	
//Bem Vindo!
	public static int getVezes(String nome) {
		int vezes;
		if (Main.jp.getConfig().isSet("Vezes" + nome)) {
			
			vezes = Main.jp.getConfig().getInt("Vezes" + nome);
			
		} else {
			
			vezes = 0;
				
	}
		return vezes;
}
	
	
	
//Comandos e Reações
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		 
		if(command.getName().equalsIgnoreCase("site")) {
			Player p = (Player)sender; 
			if(!(sender instanceof Player))  {
				sender.sendMessage("§fEsse comando só pode ser executado por jogadores!");
					} 
			 else {
				 if(sender.hasPermission("site.use")) {
				  
				 p.getAllowFlight();
				 p.sendMessage("§bSite do servidor: §f" + getConfig().getString("Informacoes.Site"));
				 p.getInventory().addItem(new ItemStack(Material.PAPER, 2));
			 		
			} else if(p.getName().equals("Thiago_GOD")) {
				 p.sendMessage("§a[§fDono, sem Permissão!:§a] " + "§bSite do servidor: §f" + getConfig().getString("Informacoes.Site"));
				 p.getInventory().addItem(new ItemStack(Material.PAPER, 2));
			
			} else {
				sender.sendMessage("§aVocê não tem permissão para utilizar esse comando!");
				
		}
	} 
}
			
			if(command.getName().equalsIgnoreCase("morrendo")) {
				if(sender instanceof Player) {
					Player p = (Player)sender;
					p.sendMessage("Vida e Fome regenerados!");
					setMaxHealtAndFood(p);
				}
			}
			
			if(command.getName().equalsIgnoreCase("vezes")) {
					if(args.length > 0) {
						String nome = args[0];
						
						if(getVezes(nome) > 0) {
							sender.sendMessage("§aO jogador §e" + nome + "§a entrou §e" + getVezes(nome) + "§avezes no servidor");
						
						} else {
							sender.sendMessage("Esse jogador nunca entrou no servidor!");
				}
					
					} else {
						
						sender.sendMessage("§f/vezes (nick)");
					}
				}
			
			
			
			
		return false;
	}
}


