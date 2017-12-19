import java.util.ArrayList;

public class GamePlay {
	ArrayList <Star> starArray;
	ArrayList <Bullet> bulletArray;
	public int totalPoints;


	public Boolean isHit() {
		Bullet bullet = null;
		boolean hit = false;
		Star star = null;
		if (starArray.size() > 0 && bulletArray.size() > 0) {
			for (int s = 0; s < starArray.size(); s++) {
				star = starArray.get(s);
				int starX = star.getXPosition();
				int starY = star.getYPosition();
				for (int b = 0; b < bulletArray.size(); b++) {
					bullet = bulletArray.get(b);
					int bulletX = bullet.getXPosition();
					int bulletY = bullet.getYPosition();
					if ((bulletX + 10) >= (starX - 10) && (bulletX - 10) <= (starX + 10)){
						if((bulletY + 10) >= (starY - 10) && (bulletY - 10) <= (starY + 10)){
							hit = true;
							bulletArray.remove(b);
							starArray.remove(s);
							break;
						}
					}
				}
				if(hit) {
					break;
				}
			}
		}
		return hit;
	}

	public void gamePoints(GamePanel panel) {
		starArray = panel.starArray;
		bulletArray = panel.bulletArray;

		if (starArray != null){
			if (isHit()) {
				totalPoints += 10;
			}
		}//end if
	}//end gamePoints
}//end class
