package com.grasernetwork.util.capes;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//import java.util.UUID;
//import java.util.logging.Level;
//
//import org.bukkit.Bukkit;
//import org.bukkit.OfflinePlayer;
//import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
//import org.bukkit.entity.Player;
//import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.scheduler.BukkitRunnable;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.interactions.Actions;
//
//import com.grasernetwork.security.CapeType;
//import com.grasernetwork.util.PlayerUtil;
//import com.mojang.authlib.GameProfile;
//import com.mojang.authlib.properties.Property;

public class Capes
{

}
//	public static final String SELECT_CAPE = "SELECT uuid,name,skin,signature,type FROM capes WHERE uuid=?, cape=?";
//	public static final String SELECT_ALL = "SELECT uuid,name,skin,signature,type FROM capes WHERE uuid=?";
//	public static final String INSERT = "INSERT INTO capes(uuid,name,skin,signature,type) VALUES (?,?,?,?,?)";
//	public static final String UPDATE = "UPDATE capes SET uuid=?,name=?,skin=?,signature=?,type=? WHERE uuid=?, cape=?";
//	public static final String DELETE = "DELETE FROM capes WHERE uuid=?";
//
////	private static LinkedList<Cape> _capeQueue = new LinkedList<Cape>();
//	private static Queue<Cape> _capeQueue = new LinkedList<Cape>();
//
//	public static void setCapeSaved(final JavaPlugin plugin, final Player target, final CapeType cape, String value, String signature)
//	{
//		if(target == null)
//		{
//			return;
//		}
//
//		GameProfile gp = ((CraftPlayer) target).getProfile();
//		gp.getProperties().clear();
//		gp.getProperties().put("textures", new Property("textures", value, signature));
//
//		//REFRESH PLAYER
////		for (Player on : Bukkit.getServer().getOnlinePlayers())
////		{
////			if(!on.equals(target))
////				on.hidePlayer(target);
////		}
////
////		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
////		{
////			@Override
////			public void run()
////			{
////				for (Player on : plugin.getServer().getOnlinePlayers())
////				{
////					if(!on.equals(target))
////						on.showPlayer(target);
////				}
////			}
////		}, 10L);
//
//		CapeLoadedEvent event = new CapeLoadedEvent(target, value, signature, cape, false);
//		Bukkit.getPluginManager().callEvent(event);
//	}
//
//	public static void setCape(final JavaPlugin plugin, final Player target, final CapeType cape)
//	{
//		for(Cape capes : _capeQueue)
//		{
//			if(capes.getTarget().getName().equals(target.getName()))
//			{
//				PlayerUtil.message(target, "Your cape is in a Queue, please be patient.");
//				return;
//			}
//		}
//
//		Cape c = new Cape(cape, target);
//		if(_capeQueue.isEmpty())
//		{
//			System.out.println("DEBUG - QUEUE Empty, adding to queue +_setting skin.");
//			_capeQueue.add(c);
//			c.setSkin(plugin, target, cape);
//			PlayerUtil.message(target, "Your cape should be visible in a few seconds.");
//			return;
//		}
//
//		System.out.println("DEBUG - QUEUE is NOT Empty, adding to queue.");
//		_capeQueue.add(c);
////		c.setSkin(plugin, target, cape);
//		PlayerUtil.message(target, "Your cape should be visible in a few SECONDS.");
//	}
//
//	private static class Cape
//	{
//		private CapeType _capeType;
//		private Player _target;
//
//		public Cape(CapeType type, Player target)
//		{
//			_capeType = type;
//			_target = target;
//		}
//
//		public CapeType getCape()
//		{
//			return _capeType;
//		}
//
//		public Player getTarget()
//		{
//			return _target;
//		}
//
//		public void setSkin(final JavaPlugin plugin, final Player target, final CapeType cape)
//		{
//			new BukkitRunnable()
//			{
//				@Override
//				public void run()
//				{
//					try
//					{
//						WebDriver driver = new HtmlUnitDriver();
//						driver.navigate().to("https://minecraft.net/profile");
//						Actions builder = new Actions(driver);
//						Actions series;
//						WebElement element;
//
//						element = driver.findElement(By.id("username"));
//						series = builder.moveToElement(element).click().sendKeys(element, cape.getEmail());
//						series.perform();
//
//						element = driver.findElement(By.id("password"));
//						series = builder.moveToElement(element).click().sendKeys(element, cape.getPassword());
//						series.perform();
//
//						element = driver.findElement(By.id("signin"));
//						element.click();
//
//						System.out.print("logged in");
//
//						// new page (security questions)
//						try
//						{
//							element = driver.findElement(By.id("answer0"));
//							series = builder.moveToElement(element).click().sendKeys(element, cape.getSecurity()[0]);
//							series.perform();
//
//							element = driver.findElement(By.id("answer1"));
//							series = builder.moveToElement(element).click().sendKeys(element, cape.getSecurity()[1]);
//							series.perform();
//
//							element = driver.findElement(By.id("answer2"));
//							series = builder.moveToElement(element).click().sendKeys(element, cape.getSecurity()[2]);
//							series.perform();
//
//							element = driver.findElement(By.tagName("button"));
//							if(element.getText().equalsIgnoreCase("Proceed"))
//							{
//								element.click();
//								System.out.print("Security passed");
//							}
//							else
//							{
//								System.out.print("Security error");
//							}
//
//							driver.navigate().to("https://minecraft.net/profile");
//						}
//						catch (Exception e) {}
//
//						//new page (profile)
//						element = driver.findElement(By.xpath("//input[@type='file']"));
//						if(element.isDisplayed())
//						{
//							System.out.println("SKIN ELEMENT EXIST");
//						}
//
//						File skinFile = null;
//						try
//						{
//							//downloading a skin
//							skinFile = new File(plugin.getDataFolder().getCanonicalPath() + "/" + target.getName() + ".png");
//							downloadFileFromURL("https://minotar.net/skin/" + target.getName() + ".png", skinFile);
//						} catch (Throwable e)
//						{
//							e.printStackTrace();
//						}
//
//						File file = skinFile;
//						if(!file.exists())
//						{
//							System.out.println("FILE DOES NOT EXIST");
//							return;
//						}
//						else
//						{
//
//							System.out.println("FILE EXISTS");
//						}
//
//						element.sendKeys(file.getAbsolutePath());
//
//						//pressing a submit button
//						element = driver.findElement(By.cssSelector("input[type='submit'][value='Upload']"));
//						if(element.isDisplayed())
//						{
//							element.click();
//
//							System.out.println("SKIN UPDATED");
//							System.out.println(file.getName());
//						}
//						else
//						{
//
//							System.out.println("SUBMIT NOT FOUND");
//						}
//
//						final Skin s = getSkinFromString(cape.getUsername());
//						if (s.getSkinName() == null)
//						{
//							System.out.println("Skin is null");
//							return;
//						}
//
//						GameProfile gp = ((CraftPlayer) target).getProfile();
//						gp.getProperties().clear();
//						gp.getProperties().put(s.getSkinName(), new Property(s.getSkinName(), s.getSkinValue(), s.getSkinSignatur()));
//
//						//uploading my old skin
//						element = driver.findElement(By.xpath("//input[@type='file']"));
//						if(element.isDisplayed())
//						{
//							System.out.println("SKIN ELEMENT EXIST");
//						}
//
//						file = new File(plugin.getDataFolder().getCanonicalPath() + "/" + cape.getUsername() + ".png");
//						if(!file.exists())
//						{
//							System.out.println("FILE DOES NOT EXIST");
//							return;
//						}
//						else
//						{
//
//							System.out.println("FILE EXISTS");
//						}
//
//						element.sendKeys(file.getAbsolutePath());
//
//						//submit button
//						element = driver.findElement(By.cssSelector("input[type='submit'][value='Upload']"));
//						if(element.isDisplayed())
//						{
//							element.click();
//
//							System.out.println("SKIN UPDATED");
//							System.out.println(file.getName());
//						}
//						else
//						{
//							System.out.println("SUBMIT NOT FOUND");
//						}
//
//						skinFile.delete();
//
//						//complete
//						driver.close();
//
//						CapeLoadedEvent event = new CapeLoadedEvent(target, s.getSkinValue(), s.getSkinSignatur(), cape, true);
//						Bukkit.getPluginManager().callEvent(event);
//
//						new BukkitRunnable()
//						{
//
//							@Override
//							public void run()
//							{
//								_capeQueue.poll();
//
//								if(!_capeQueue.isEmpty())
//								{
//									Cape c = _capeQueue.peek();
//									setSkin(plugin, c.getTarget(), c.getCape());
//								}
//							}
//						}.runTaskLater(plugin, 1450L);
//
//					} catch (Exception e)
//					{
//						e.printStackTrace();
//					}
//				}
//			}.runTaskAsynchronously(plugin);
//		}
//
//		public static void downloadFileFromURL(String urlString, File destination)
//				throws Throwable
//		{
//
//			URL website = new URL(urlString);
//			try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
//					FileOutputStream fos = new FileOutputStream(destination);)
//			{
//				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//			}
//		}
//
//		private static Skin getSkinFromString(String name)
//		{
//			OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(name);
//			Skin s = new Skin(p.getUniqueId());
//			return s;
//		}
//	}
//
//	private static class Skin
//	{
//		String uuid;
//		UUID asUUID;
//		String name;
//		String value;
//		String signatur;
//		String playername;
//
//		Skin(UUID uuid)
//		{
//			this.uuid = uuid.toString().replaceAll("-", "");
//			this.asUUID = uuid;
//			load();
//		}
//
//		Skin(String name, String value, String signature)
//		{
//			this.name = name;
//			this.value = value;
//			this.signatur = signature;
//		}
//
//		private void load()
//		{
//			try
//			{
//				OfflinePlayer offP = Bukkit.getServer().getOfflinePlayer(this.asUUID);
//				this.playername = offP.getName();
//
//				URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid + "?unsigned=false");
//				URLConnection uc = url.openConnection();
//				uc.setUseCaches(false);
//				uc.setDefaultUseCaches(false);
//				uc.addRequestProperty("User-Agent", "Mozilla/5.0");
//				uc.addRequestProperty("Cache-Control", "no-cache, no-store, must-revalidate");
//				uc.addRequestProperty("Pragma", "no-cache");
//
//				String json = new Scanner(uc.getInputStream(), "UTF-8").useDelimiter("\\A").next();
//				JSONParser parser = new JSONParser();
//				Object obj = parser.parse(json);
//				JSONArray properties = (JSONArray) ((JSONObject) obj).get("properties");
//				for (int i = 0; i < properties.size(); i++)
//				{
//					try
//					{
//						JSONObject property = (JSONObject) properties.get(i);
//						String name = (String) property.get("name");
//						String value = (String) property.get("value");
//						String signature = property.containsKey("signature") ? (String) property.get("signature") : null;
//
//						this.name = name;
//						this.value = value;
//						this.signatur = signature;
//					} catch (Exception e)
//					{
//						Bukkit.getLogger().log(Level.WARNING, "Failed to apply auth property", e);
//					}
//				}
//				uc.setConnectTimeout(0);
//				uc.getInputStream().close();
//			} catch (Exception localException1)
//			{
//			}
//		}
//
//		public String getSkinValue()
//		{
//			return this.value;
//		}
//
//		public String getSkinName()
//		{
//			return this.name;
//		}
//
//		public String getSkinSignatur()
//		{
//			return this.signatur;
//		}
//
//		public String getUUID()
//		{
//			return this.uuid;
//		}
//
//		public UUID getAsUUID()
//		{
//			return this.asUUID;
//		}
//
//		public String getPlayerName()
//		{
//			return this.playername;
//		}
//	}
//}
