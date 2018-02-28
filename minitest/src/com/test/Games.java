package com.test;

import java.util.Scanner;

public class Games {
	static final int SIZE = 4;

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
		int[] random = new int[2];
		random[0] = (int) (Math.random() * 4);
		random[1] = (int) (Math.random() * 4);
		return random;
	}

	// 비어있는곳에 랜덤으로 숫자놓기
	public static Tile[][] putRandom(Tile[][] tile) {

		while (isEmpty(tile)) {
			int[] ran = random(); // ran[0]:세로 ran[1]:가로

			if (tile[ran[0]][ran[1]].getNum() == 0) {
				tile[ran[0]][ran[1]].setNum(2);
				System.out.println("============");
				return tile;
			}
		}
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

			case 4:
				printTile(moveLeft(tile));
				break;
			case 6:
				printTile(moveRight(tile));
				break;
			case 8:
				printTile(moveUp(tile));
				break;
			case 2:
				printTile(moveDown(tile));
				break;
			default:
				break;
			}
		} while (true);

	}

	public static Tile[][] moveLeft(Tile[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {

				if (tile[i][j].getNum() != 0) {
					if (j == 0) {

					} else {

						for (int k = 0; k <= SIZE; k++) {
							for (int l = 0; l < j; l++) {
								if ((tile[i][j].getNum() == tile[i][l].getNum()) || ((tile[i][l].getNum() == 0) && (tile[i][j].getNum() != 0))) {
									tile[i][l].setNum(tile[i][j].getNum() + tile[i][l].getNum());
									tile[i][j].setNum(0);
									break;
								}
							}
						}
						/*
						 * for (int k = 0; k <= j; k++) { if
						 * ((tile[i][j].getNum() == tile[i][k].getNum()) |
						 * ((tile[i][k].getNum() == 0) &&(tile[i][j].getNum() !=
						 * 0))) {
						 * System.out.println("tile[i][j].getNum()"+tile[i][j].
						 * getNum());
						 * System.out.println("tile[i][k].getNum()"+tile[i][k].
						 * getNum());
						 * System.out.println("tile[i][k].getNum() == 0"+(tile[i
						 * ][k].getNum() == 0));
						 * System.out.println("tile[i][j].getNum() != 0"+(tile[i
						 * ][j].getNum() != 0));
						 * System.out.println(i+","+j+","+k);
						 * tile[i][k].setNum(tile[i][j].getNum() +
						 * tile[i][k].getNum()); tile[i][j].setNum(0);
						 * 
						 * break; } }
						 */
						/*
						 * if(tile[i][j].getNum()==tile[i][0].getNum()){
						 * tile[i][0].setNum(tile[i][j].getNum() +
						 * tile[i][0].getNum()); tile[i][j].setNum(0); }else
						 * if(tile[i][j].getNum() == tile[i][1].getNum()){
						 * tile[i][0].setNum(tile[i][j].getNum() +
						 * tile[i][1].getNum()); tile[i][j].setNum(0); }else
						 * if(tile[i][j].getNum() == tile[i][2].getNum()){
						 * tile[i][0].setNum(tile[i][j].getNum() +
						 * tile[i][2].getNum()); tile[i][j].setNum(0); }
						 */
					}

				}
			}
		}
		return tile;
	}

	public static Tile[][] moveUp(Tile[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j].getNum() != 0) {
					if (i == 0) {

					} else {
						
						for (int k = 0; k <= SIZE; k++) {
							for (int l = 0; l < i; l++) {
								if ((tile[i][j].getNum() == tile[l][j].getNum()) || ((tile[l][j].getNum() == 0) && (tile[i][j].getNum() != 0))) {
									tile[l][j].setNum(tile[i][j].getNum() + tile[l][j].getNum());
									tile[i][j].setNum(0);
									break;
								}
							}
						}
						
						/*
						tile[0][j].setNum(tile[i][j].getNum() + tile[0][j].getNum());
						tile[i][j].setNum(0);
						*/
						
					}
				}
			}
		}
		return tile;
	}

	public static Tile[][] moveDown(Tile[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j].getNum() != 0) {
					if (i == 3) {

					} else {
						
						tile[3][j].setNum(tile[i][j].getNum() + tile[3][j].getNum());
						tile[i][j].setNum(0);
					}
				}
			}
		}
		return tile;
	}

	public static Tile[][] moveRight(Tile[][] tile) {
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {
				if (tile[i][j].getNum() != 0) {
					if (j == 3) {

					} else {
						
						for (int k = SIZE-1; k >=0 ; k--) {
							for (int l = SIZE-1; l >= 0; l--) {
								if (((tile[i][j].getNum()!=0&&(tile[i][l].getNum()!=0))&& (tile[i][j].getNum()==tile[i][l].getNum()) ) 
										|| ((tile[i][l].getNum() == 0) && (tile[i][j].getNum() != 0))) {
									
									System.out.println(i+", "+j+", "+k+", "+l);
									System.out.println("tile[i][j].getNum()"+tile[i][j].getNum());
									System.out.println("tile[i][l].getNum()"+tile[i][l].getNum());
									System.out.println("(tile[i][l].getNum() == 0"+(tile[i][l].getNum() == 0));
									System.out.println("(tile[i][j].getNum() != 0))"+(tile[i][j].getNum() != 0));
									tile[i][l].setNum(tile[i][j].getNum() + tile[i][l].getNum());
									tile[i][j].setNum(0);
									break;
								}
							}
						}
						
						/*
						tile[i][3].setNum(tile[i][j].getNum() + tile[i][3].getNum());
						tile[i][j].setNum(0);
						*/
						
					}
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
