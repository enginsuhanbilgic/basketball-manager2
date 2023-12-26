package player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PlayerInit {
	
	public static void playerInit() {
		
		List<Player> players = new ArrayList<>();
		List<Player> Cplayers = new ArrayList<>();
		List<Player> PFplayers = new ArrayList<>();
		List<Player> PGplayers = new ArrayList<>();
		List<Player> SFplayers = new ArrayList<>();
		List<Player> SGplayers = new ArrayList<>();
		
		int previousIndex = 0;
		try(BufferedReader br = new BufferedReader(new FileReader("src//player//PlayerStatsUnfiltered.csv"))){
			String line;
			while((line = br.readLine()) != null) {
				String[] data = line.split(",");
				
				if(!data[0].equals("Rk")) {
					String position = "";
					String name = "";
					double points = 0;
					double rebounds = 0;
					double assists = 0;
					double blocks = 0;
					double steals = 0;
					if(Integer.parseInt(data[0]) == previousIndex + 1) {
						name = data[1];
						position = data[2];
						points = Double.parseDouble(data[29]);
						rebounds = Double.parseDouble(data[23]);
						assists = Double.parseDouble(data[24]);
						blocks = Double.parseDouble(data[26]);
						steals = Double.parseDouble(data[25]);
						//System.out.println(name + " Position: " + position);
						previousIndex ++;
					}
					switch(position) {
					case "C":
						C playerC = new C(name, position, points, rebounds, assists, blocks, steals);
						players.add(playerC);
						Cplayers.add(playerC);
						break;
					case "PF":
						PF playerPF = new PF(name, position, points, rebounds, assists, blocks, steals);
						players.add(playerPF);
						PFplayers.add(playerPF);
						break;
					case "PG":
						PG playerPG = new PG(name, position, points, rebounds, assists, blocks, steals);
						players.add(playerPG);
						PGplayers.add(playerPG);
						break;
					case "SF":
						SF playerSF = new SF(name, position, points, rebounds, assists, blocks, steals);
						players.add(playerSF);
						SFplayers.add(playerSF);
						break;
					case "SG":
						SG playerSG = new SG(name, position, points, rebounds, assists, blocks, steals);
						players.add(playerSG);
						SGplayers.add(playerSG);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Input file exception");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//PlayerStatsFiltered.csv"))){

			for(Player player : players) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", player.getName(), player.getPosition(), player.getPoints(), player.getRebounds(), player.getAssists(), player.getBlocks(), player.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to PlayerStatsFiltered.csv");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//CPlayerStats.csv"))){

			for(Player Cplayer : Cplayers) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", Cplayer.getName(), Cplayer.getPosition(), Cplayer.getPoints(), Cplayer.getRebounds(), Cplayer.getAssists(), Cplayer.getBlocks(), Cplayer.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to CPlayerStats.csv");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//PFPlayerStats.csv"))){

			for(Player PFplayer : PFplayers) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", PFplayer.getName(), PFplayer.getPosition(), PFplayer.getPoints(), PFplayer.getRebounds(), PFplayer.getAssists(), PFplayer.getBlocks(), PFplayer.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to PFPlayerStats.csv");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//PGPlayerStats.csv"))){

			for(Player PGplayer : PGplayers) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", PGplayer.getName(), PGplayer.getPosition(), PGplayer.getPoints(), PGplayer.getRebounds(), PGplayer.getAssists(), PGplayer.getBlocks(), PGplayer.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to PGPlayerStats.csv");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//SFPlayerStats.csv"))){

			for(Player SFplayer : SFplayers) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", SFplayer.getName(), SFplayer.getPosition(), SFplayer.getPoints(), SFplayer.getRebounds(), SFplayer.getAssists(), SFplayer.getBlocks(), SFplayer.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to SFPlayerStats.csv");
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src//player//SGPlayerStats.csv"))){

			for(Player SGplayer : SGplayers) {
				bw.write(String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%.2f", SGplayer.getName(), SGplayer.getPosition(), SGplayer.getPoints(), SGplayer.getRebounds(), SGplayer.getAssists(), SGplayer.getBlocks(), SGplayer.getSteals()));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured while writing to SGPlayerStats.csv");
		}
		
		
	}
	
}
