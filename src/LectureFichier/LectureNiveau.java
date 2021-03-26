package LectureFichier;

import Niveau.Niveau;

public class LectureNiveau {
	private String nomFichier;
	private Niveau niveau;
	private ScannerFichier scannerDeFichier;
	
	public LectureNiveau(String nomfic) {
		nomFichier = nomfic;
	}
	
	public Niveau lectureDuNiveau() {
		scannerDeFichier = new ScannerFichier(nomFichier);
		niveau = new Niveau();
		try {
			for (int i=0;i<2;i++) {
				switch(scannerDeFichier.lectureLigne()) {
				
				case "//MAP": niveau.setMap(lectureMap());
				break;
				case "//SCORE": lectureScore();
				break;
				case "": i--;
				break;
				default: System.out.println("Fin");
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
			niveau = null;
		}
		
		scannerDeFichier.close();
		return niveau;
	}

	
	private boolean[][] lectureMap() {
		boolean[][] map = new boolean[Niveau.getLargeurMap()][Niveau.getLongueurMap()];
		String  ligneString;
		try {
			
			for (int largeur=0; largeur<Niveau.getLargeurMap(); largeur++ ) {
				ligneString = scannerDeFichier.lectureLigne();
				
				if (ligneString.length() != Niveau.getLongueurMap()) {
					throw new Exception("Erreur : La ligne " + ligneString + " n'est pas a la bonne taille");
				}
				
				for (int longueur=0; longueur < Niveau.getLongueurMap(); longueur++ ) {
					map[largeur][longueur] = ligneString.charAt(longueur) == '1';
				}
				
			}
		}catch(Exception e) {
			System.out.println("Erreur : contenue pas au norme");
			System.out.println(e);
			map = null;
		}
		return map;
	}
	
	private void lectureScore() {
		try {
			String ligneString = scannerDeFichier.lectureLigne();
			niveau.setMeilleurTempsEnSeconde(Integer.parseInt(ligneString));
			ligneString = scannerDeFichier.lectureLigne();
			niveau.setMeilleurScore(Integer.parseInt(ligneString));
			
		}catch(Exception e){
			System.out.println("Erreur : lecture Score impossible");
			System.out.println(e);
			niveau.setMeilleurTempsEnSeconde(0);
			niveau.setMeilleurTempsEnSeconde(0);
		}

	}
}
