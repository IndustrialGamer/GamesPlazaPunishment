package com.enjin.gamesplazabrasil;


import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class GUI extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    registerConfig();
    }


    ArrayList<Player> mesagenPun = new ArrayList<Player>();
    ArrayList <Player> banReason = new ArrayList<Player>();




    public void registerConfig(){

        this.getConfig().options().copyDefaults(true);
        saveConfig();

    }

    void onPlayerPreLoginEvent (PlayerLoginEvent e) {

        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(e.getPlayer().getName()) == true){

            e.setKickMessage("You are still banned!");
            e.disallow (PlayerLoginEvent.Result.KICK_FULL, e.getKickMessage());
        } else {
            e.allow();
            return;

        }


    }



    String target;


 private void openGUI(Player p){
     Inventory inv1 = Bukkit.createInventory(null, 54, ChatColor.GREEN + "Punir");

     ItemStack banir = new ItemStack(Material.REDSTONE_BLOCK);
     ItemMeta banirMeta = banir.getItemMeta();

     ItemStack tempbanir = new ItemStack(Material.REDSTONE);
     ItemMeta tempbanirMeta = tempbanir.getItemMeta();

     ItemStack mute = new ItemStack(Material.LAPIS_BLOCK);
     ItemMeta muteMeta = mute.getItemMeta();

     ItemStack tempmute = new ItemStack(Material.LEATHER);
     ItemMeta tempmuteMeta = tempmute.getItemMeta();

    ItemStack warn1 = new ItemStack(Material.INK_SACK);
    ItemMeta warn1Meta = warn1.getItemMeta();

     ItemStack warn2 = new ItemStack(Material.INK_SACK);
     ItemMeta warn2Meta = warn2.getItemMeta();

     ItemStack warn3 = new ItemStack(Material.INK_SACK);
     ItemMeta warn3Meta = warn3.getItemMeta();

     ItemStack kick = new ItemStack(Material.EMERALD_BLOCK);
     ItemMeta kickMeta = kick.getItemMeta();

     ItemStack fechar = new ItemStack(Material.ARROW);
     ItemMeta fecharMeta = fechar.getItemMeta();

     /*
     ItemStack penseBem = new ItemStack(Material.GLASS);
     ItemMeta penseBemMeta = penseBem.getItemMeta();
*/

     banirMeta.setDisplayName(ChatColor.DARK_RED + "Banir");
     banir.setItemMeta(banirMeta);

     tempbanirMeta.setDisplayName(ChatColor.RED + "Banir Temporariamente [WIP]");
     tempbanir.setItemMeta(tempbanirMeta);

     muteMeta.setDisplayName(ChatColor.DARK_GREEN + "Mutar");
     mute.setItemMeta(muteMeta);

     tempmuteMeta.setDisplayName(ChatColor.GREEN + "Mutar Temporariamente [WIP]");
     tempmute.setItemMeta(tempmuteMeta);

     warn1Meta.setDisplayName(ChatColor.GOLD + "Warning Severidade 1");
     warn1.setItemMeta(warn1Meta);

     warn2Meta.setDisplayName(ChatColor.GRAY + "Warning Severidade 2");
     warn2.setItemMeta(warn2Meta);

     warn3Meta.setDisplayName(ChatColor.DARK_GRAY + "Warning Severidade 3");
     warn3.setItemMeta(warn3Meta);

     kickMeta.setDisplayName(ChatColor.DARK_GREEN + "Kickar");
     kick.setItemMeta(kickMeta);

     fecharMeta.setDisplayName(ChatColor.DARK_BLUE + "Fechar GUI de Punicoes");
     fechar.setItemMeta(fecharMeta);
/*
     penseBemMeta.setDisplayName(ChatColor.DARK_BLUE + "PENSE BEM");
    penseBem.setItemMeta(penseBemMeta); */

     inv1.setItem(1, warn1);
     inv1.setItem(4, warn2);
     inv1.setItem(7, warn3);
     inv1.setItem(39, banir);
     inv1.setItem(41, tempbanir);
     inv1.setItem(47, tempmute);
     inv1.setItem(51, mute);
     inv1.setItem(53, fechar);
     inv1.setItem(13, kick);
   //  inv1.setItem(2, penseBem);


     p.openInventory(inv1);


 }

 public void openPBGUI(Player p){

     Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL, "PENSE BEM!");

     ItemStack sim = new ItemStack(Material.EMERALD_BLOCK);
     ItemMeta simMeta = sim.getItemMeta();

     simMeta.setDisplayName(ChatColor.GREEN + "SIM");
     sim.setItemMeta(simMeta);

     ItemStack nao = new ItemStack(Material.REDSTONE_BLOCK);
     ItemMeta naoMeta = nao.getItemMeta();

     naoMeta.setDisplayName(ChatColor.DARK_RED + "NAO");
     nao.setItemMeta(naoMeta);

     anvil.setItem(0, nao);
     anvil.setItem(2, sim);

     p.openInventory(anvil);

 }






@EventHandler (priority = EventPriority.NORMAL)
void onPlayerChatSetMute(AsyncPlayerChatEvent e) {

String mutado = this.getConfig().getString(e.getPlayer().getName());
saveConfig();

    if (mutado == "mute") {

        e.setCancelled(true);
        e.setMessage("");
        e.getPlayer().sendMessage(ChatColor.GOLD + "Punicao> " + ChatColor.AQUA + "Voce esta mutado!");
        return;
    } else {

        e.setCancelled(false);

    }


}

@EventHandler(priority = EventPriority.LOW)
void onPlayerChatSendMes(AsyncPlayerChatEvent e){

     if(mesagenPun.contains(e.getPlayer())){
         e.setCancelled(true);
         e.setMessage(null);
         e.getPlayer().sendMessage(ChatColor.DARK_RED + "Punicao> " + ChatColor.RED + "Voce foi punido! Isso ocorre com a quebra de regras do servidor. Elas podem ser consultadas com o comando /regras. Para se livrar dessa mensagem e poder falar novamente no chat (a nao ser que tenha recebido outra punicao) digite 'entendido' no chat.");
         return;


     } else {
         e.setCancelled(false);
     }

}
@EventHandler(priority = EventPriority.LOWEST)
void onPlayerChatLibChat(AsyncPlayerChatEvent e){

if(mesagenPun.contains(e.getPlayer())) {
    e.setCancelled(true);
    if (e.getMessage().equalsIgnoreCase("entendido")) {
        mesagenPun.remove(e.getPlayer());
        e.getPlayer().sendMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GREEN + "Tudo bem, voce esta livre! Se esforce para nao quebrar as regras novamente :)");
        return;
    }
  } else {
    e.setCancelled(false);
  }
}


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("punir")) {
            if (!(p.hasPermission("adm.punish.openGUI"))) {
                p.sendMessage(ChatColor.BLUE + "Punir> " + ChatColor.RED + "Voce nao tem permissao para punir outro player!");
                p.closeInventory();
                return true;
            }

            if (args.length == 0) {
                p.sendMessage(ChatColor.BLUE + "Punir> " + ChatColor.RED + "Sem argumentos!");
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.BLUE + "Punir> " + ChatColor.RED + "Muitos argumentos!");
            }
            if (args.length == 1) {

                target = args[0];

                openGUI(p);

            }

        }


        Player targetP = Bukkit.getPlayer(args[0]);

       //mesagenPun.add(targetP);


        if (command.getName().equalsIgnoreCase("mutar")) {
            if (!p.hasPermission("punish.chat.mute")) {
                p.sendMessage(ChatColor.BLUE + "Mutar> " + ChatColor.RED + "Voce nao tem permissao!");
            }


            target = args[0];

            if (args.length == 0) {
                p.sendMessage(ChatColor.BLUE + "Mutar> " + ChatColor.RED + "Sem argumentos!");
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.BLUE + "Mutar> " + ChatColor.RED + "Muitos argumentos!");
            }
            if (args.length == 1) {

                if (target == null) {
                    p.sendMessage(ChatColor.BLUE + "Mutar> " + ChatColor.RED + "Não foi possivel localizar o player " + ChatColor.DARK_PURPLE + args[0]);

                } else {

                    p.sendMessage(ChatColor.BLUE + "Mutar> " + ChatColor.GREEN + "O player " + ChatColor.DARK_PURPLE + target + ChatColor.GREEN + " foi mutado!");
                    Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Mutar> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + target + ChatColor.AQUA + " foi mutado");
                    this.getConfig().set(target, "mute");
                    saveConfig();

                    Player targetWarned = Bukkit.getPlayer(args[0]);
                    mesagenPun.remove(p);

                    mesagenPun.add(targetWarned);

                }


            }

        }


        if (command.getName().equalsIgnoreCase("warn")) {

            if (args.length < 1) {

                p.sendMessage(ChatColor.BLUE + "Warn> " + ChatColor.RED + "Para dar warning em players use /warn <player>");

            }
            if (args.length == 1) {

                String niv = this.getConfig().getString(target + "Warn");

                try {
                    Integer l = Integer.parseInt(niv.toString());

                    Player targetWarned = Bukkit.getPlayer(args[0]);

                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nível 1");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + targetWarned.getName() + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 1)");
                    for (Player pl : Bukkit.getOnlinePlayers()){

                        pl.playSound(pl.getLocation(), Sound.BLOCK_ANVIL_FALL, 2f, 02f);

                    }
                    this.getConfig().set(targetWarned.getName() + "Warn", (getConfig().getInt(targetWarned.getName() + "Warn") + 1) );
                    this.saveConfig();


                    mesagenPun.remove(p);
                    mesagenPun.add(targetWarned);

                    if(getConfig().getInt(targetWarned.getName() + "Warn") >= 10){

                       Bukkit.getBanList(BanList.Type.NAME).addBan(targetWarned.getName(), "You were banned by an operator", null, p.getName());
                        p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Voce foi banido por obter mais de 10 pontos de warning. Caso queira voltar a jogar, recorra de sua punicao em http://gamesplazabrasil.enjin.com/home");

                    }


                } catch (Exception e) {
                    e.getStackTrace();
                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nivel 1");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + target + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 1)");
                    return true;

                }

            }


        }

        if (command.getName().equalsIgnoreCase("warn2")) {

            if (args.length < 1) {

                p.sendMessage(ChatColor.BLUE + "Warn> " + ChatColor.RED + "Para dar warning em players use /warn <player>");

            }
            if (args.length == 1) {

                String niv = this.getConfig().getString(target + "Warn");

                try {
                    Integer l = Integer.parseInt(niv.toString());

                    Player targetWarned = Bukkit.getPlayer(args[0]);

                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nível 2");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + target + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 2)");
                    this.getConfig().set(targetWarned.getName() + "Warn", (getConfig().getInt(targetWarned.getName() + "Warn") + 2) );
                    this.saveConfig();
                    for (Player pl : Bukkit.getOnlinePlayers()){

                        pl.playSound(pl.getLocation(), Sound.BLOCK_ANVIL_FALL, 2f, 02f);

                    }

                    mesagenPun.remove(p);
                    mesagenPun.add(targetWarned);

                    if(getConfig().getInt(targetWarned.getName() + "Warn") > 10){

                        Bukkit.getBanList(BanList.Type.NAME).addBan(targetWarned.getName(), "You were banned by an operator", null, p.getName());
                        p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Voce foi banido por obter mais de 10 pontos de warning. Caso queira voltar a jogar, recorra de sua punicao em http://gamesplazabrasil.enjin.com/home");

                    }


                } catch (Exception e) {
                    e.getStackTrace();
                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nível 2");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + target + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 2)");

                    return true;

                }

            }
        }
        if (command.getName().equalsIgnoreCase("warn3")) {

            if (args.length < 1) {

                p.sendMessage(ChatColor.BLUE + "Warn> " + ChatColor.RED + "Para dar warning em players use /warn <player>");

            }
            if (args.length == 1) {

                String niv = this.getConfig().getString(target + "Warn");

                try {
                    Player targetWarned = Bukkit.getPlayer(args[0]);


                    Integer l = Integer.parseInt(niv.toString());
                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nível 3");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + target + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 3)");
                    this.getConfig().set(targetWarned.getName() + "Warn", (getConfig().getInt(targetWarned.getName() + "Warn") + 3) );
                    this.saveConfig();

                    mesagenPun.remove(p);
                    mesagenPun.add(targetWarned);


                    if(getConfig().getInt(targetWarned.getName() + "Warn") >= 10){

                        Bukkit.getBanList(BanList.Type.NAME).addBan(targetWarned.getName(), "You were banned by an operator", null, p.getName());
                        p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Voce foi banido por obter mais de 10 pontos de warning. Caso queira voltar a jogar, recorra de sua punicao em http://gamesplazabrasil.enjin.com/home");

                    }

                } catch (Exception e) {
                    e.getStackTrace();
                    p.sendMessage(ChatColor.BLUE + "Warn>" + ChatColor.GREEN + "O player recebeu o warning de nível 3");
                    Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Punicao> " + ChatColor.GRAY + "O player " + target + " recebeu uma punicao!" + ChatColor.DARK_RED + "(Warning nível 3)");

                    return true;

                }

            }
        }

        if(command.getName().equalsIgnoreCase("tempBan")){

try{
            Player target = Bukkit.getPlayer(args[0]);


            if(args.length == 0){
                p.sendMessage(ChatColor.BLUE + "Banir> " + ChatColor.RED + "Para banir um player temporariamente, use /tempBan <player> <tempo> <MSDHMS>");
                return true;

            }
            if(args.length > 3){

                p.sendMessage(ChatColor.BLUE + "Banir> " + ChatColor.RED + "Para banir um player temporariamente, use /tempBan <player> <tempoMSDHMS)");
                return true;
            }
            if(args.length < 3){
                p.sendMessage(ChatColor.BLUE + "Banir> " + ChatColor.RED + "Para banir um player temporariamente, use /tempBan <player> <tempoMSDHMS)");
                return true;
            }
            if(args.length == 3) {

                Date time = new Date(System.currentTimeMillis());

                Integer tempo = Integer.parseInt(args[1]);

                if (args[2].equals("M")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60) * 60) * 60) * 24) * 7) * 30);

                    if (end.equals(System.currentTimeMillis())) {
                       Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }
                if (args[2].equals("S")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60) * 60) *60) * 24) * 7));

                    if (end.equals(System.currentTimeMillis())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }
                if (args[2].equals("D")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60) * 60) * 60) * 24)));

                    if (end.equals(System.currentTimeMillis())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }
                if (args[2].equals("H")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60) * 60) * 60))));

                    if (end.equals(System.currentTimeMillis())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }
                if (args[2].equals("m")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60) * 60)))));

                    if (end.equals(System.currentTimeMillis())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }

                if (args[2].equals("s")) {
                    Date end = new Date(System.currentTimeMillis() + (((((tempo * 60))))));

                    if (end.equals(System.currentTimeMillis())) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());

                    } else {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", end, p.getName());
                        p.kickPlayer(target.getName());
                    }
                }
            }


            }catch (Exception e){
                e.printStackTrace();
                p.sendMessage(ChatColor.GOLD + "TempBan> " + ChatColor.AQUA + "O player " + ChatColor.DARK_PURPLE + targetP.getName() + ChatColor.AQUA +  "foi banido por " + args[2] + " " + args[1]);
   // Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "You were banned by an operator", null, p.getName());
                targetP.kickPlayer(ChatColor.GOLD + "TempBan> " + ChatColor.AQUA +  "Voce foi banido por " + args[2] + " " + args[1]);


            }


            }

        //Comando ban
        if (command.getName().equalsIgnoreCase("ban")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Sem argumentos!");
                return  true;

            } else if (args.length < 2){

                p.sendMessage(ChatColor.BLUE + "Banir> " + "Use /ban <player> <mensagemDeBan>");

            } else {
                if (args.length == 2) {
                    if (p.hasPermission("op.ban.ban")) {
                        for (Player target : Bukkit.getServer().getOnlinePlayers()) {

                            String banMessage = args[1];
                            target.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.translateAlternateColorCodes('&', banMessage));

                            if (target.getName().equalsIgnoreCase(args[0])) {

                                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),banMessage , null , p.getDisplayName()  );
                                target.kickPlayer(ChatColor.BLUE + "Ban> " + ChatColor.BOLD + "Voce foi banido por " + ChatColor.DARK_RED + p.getName());

                                Bukkit.getServer().broadcastMessage(ChatColor.BLUE + "Ban> " + ChatColor.BOLD + " " +  ChatColor.GOLD + "O player " + target.getName() + "  foi banido por " + ChatColor.RED + p.getName());

                                p.sendMessage( ChatColor.BLUE + "Ban> " + ChatColor.GREEN + "Voce baniu o player " + target.getName());


                                target.sendMessage(ChatColor.BLUE + "Ban> " + "Voce foi banido por " + ChatColor.RED + p.getName());
                            } else if(!(target.getName().equalsIgnoreCase(args[0]))){
                                p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Player nao encontrado");
                                return  true;
                            }

                        }
                    } else{

                        p.sendMessage(ChatColor.BLUE + "Ban> " + ChatColor.RED + "Voce nao tem permissao para banir um player");
                        return  true;

                    }
                }

            }
        }






        return false;

    }

    String comando;

    @EventHandler
    void onInventoryClick(InventoryClickEvent e){

        if(!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Punir")){
            return;
        }

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if(!(e.getCurrentItem() != null || e.getCurrentItem().getType() != Material.AIR || e.getCurrentItem().hasItemMeta())){
            p.closeInventory();
            return;
        }



        switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())){


            case "Banir":
                //p.performCommand("ban " + target); p.closeInventory();
                openPBGUI(p);
                banReason.add(p);

                comando = "ban " + target + "Voce foi banido por " + p.getName();

            break;

            case "Kickar":
              //  p.performCommand("kick " + target); p.closeInventory();
                openPBGUI(p);
                comando = "kick " + target;

                break;
            case "Mutar":
              //  p.performCommand("mutar " + target);
                openPBGUI(p);
                comando = "mutar " + target;

                break;
            case "Warning Severidade 1":
               // p.performCommand("warn " + target);

                openPBGUI(p);
                comando = "warn " + target;

                break;
            case "Warning Severidade 2":
              //  p.performCommand("warn2 " + target);

                openPBGUI(p);
                comando = "warn2 " + target;


                break;
            case "Warning Severidade 3":
              //  p.performCommand("warn3 " + target);

                openPBGUI(p);
                comando = "warn3 " + target;


                break;
            case "Mutar Temporariamente [WIP]":
               p.performCommand("wip");

                break;
            case "Banir Temporariamente [WIP]":
                p.sendMessage(ChatColor.GOLD + "TempBan> " + ChatColor.AQUA + "A funcao de banir temporariamente pela GUI está [WIP]. Caso queira banir alguem temporariamente, use /tempban <player> <tempo>");

                break;
            case "Fechar GUI de Punicoes":

                p.closeInventory();

                break;

                }

        }
    @EventHandler
    void onInventoryClick2(InventoryClickEvent e){

        if(!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("PENSE BEM!")){
            return;
        }

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if(!(e.getCurrentItem() != null || e.getCurrentItem().getType() != Material.AIR || e.getCurrentItem().hasItemMeta())){
            p.closeInventory();
            return;
        }

        switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())){


            case "SIM": p.closeInventory(); p.performCommand(comando); break;
            case "NAO": p.closeInventory(); openGUI(p); break;




        }

    }






    }