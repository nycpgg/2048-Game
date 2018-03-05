package com.test;

import java.util.Scanner;

public class Games {
	static final int SIZE = 4; // 칸 수
	static int score = 0, highScore = 0; // 현재 점수, 최고 점수

	public static boolean isEmpty(Tile[][] tile) {
		boolean result = false;

		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j].getNum() == 0) {
					result = true;
				}
			}
		}
		return result;
	}

	// 랜덤 추출
	public static int[] random() {
		int[] random = new int[3];
		random[0] = (int) (Math.random() * 4);
		random[1] = (int) (Math.random() * 4);
		random[2] = (int) (Math.random() * 2)+1;
		
		return random;
	}

	// 비어있는곳에 랜덤으로 숫자놓기
	public static Tile[][] putRandom(Tile[][] tile) {

		while (isEmpty(tile)) {
			int[] ran = random(); // ran[0]:세로 ran[1]:가로

/*		null check
			for(Tile[] checkZero : tile){
				for(Tile checkZero1 : checkZero){
					System.out.println(checkZero1.getNum());
				}
			}
*/
			if (tile[ran[0]][ran[1]].getNum() == 0) {
				tile[ran[0]][ran[1]].setNum(ran[2]*2);
				System.out.println("============");
				return tile;
			}
		}
		System.out.println("더 이상 놓을자리가 없습니다");
		System.out.println("게임 종료");
		System.exit(0);
		return tile;
	}

	public static Tile[][] startGame() {

		Tile[][] tile = new Tile[4][4];

		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				tile[i][j] = new Tile();
			}
		}
		return tile;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Tile[][] tile = startGame();

		do {
			tile = putRandom(tile);
			printTile(tile);

			int inputKey = sc.nextInt();

			switch (inputKey) {

			case 4: // 왼쪽
				moveTile(tile, 0, 0, -1);
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 6: // 오른쪽
				moveTile(tile, SIZE * SIZE - 1, 0, 1);
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 8: // 위
				printTile(moveTile(tile, 0, -1, 0));
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 2: // 아래
				moveTile(tile, SIZE * SIZE - 1, 1, 0);
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			default:
				break;
			}
		} while (true);

	}

	public static Tile[][] moveTile(Tile[][] tile, int reveres, int row, int col) {

		for (int k = 0; k < SIZE * SIZE; k++) {

			int temp = Math.abs(reveres - k);

			int i = temp / SIZE;
			int j = temp % SIZE;

			if (tile[i][j].getNum() == 0) {
				continue;
			}

			int nextRow = i + row;
			int nextCol = j + col;

			while (nextRow >= 0 && nextRow < SIZE && nextCol >= 0 && nextCol < SIZE) {
				Tile next = tile[nextRow][nextCol];

				// tile[i][j]가 0이 아니고 next가 0일때
				if (next.getNum() == 0) {
					next.setNum(next.getNum() + tile[i][j].getNum());
					tile[i][j].setNum(0);

					i = nextRow;
					j = nextCol;
					nextRow += row;
					nextCol += col;

				} else if (tile[i][j].getNum() == next.getNum()) {

					next.setNum(next.getNum() + tile[i][j].getNum());
					score += next.getNum();
					tile[i][j].setNum(0);
					
					i = nextRow;
					j = nextCol;
					nextRow += row;
					nextCol += col;
					
					if (score > highScore) {
						highScore = score;
					}
					break;
				} else {
					break;
				}

			}
		}

		return tile;
	}

	public static void printTile(Tile[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				System.out.print(tile[i][j]);
			}
			System.out.println();
		}
	}

}