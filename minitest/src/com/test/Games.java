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

			case 4: // 왼쪽
				printTile(moveTile(tile, 0, 0, -1));
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 6: // 오른쪽
				printTile(moveTile(tile, SIZE * SIZE - 1, 0, 1));
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 8: // 위
				printTile(moveTile(tile, 0, -1, 0));
				System.out.println("최고점수 : " + highScore);
				System.out.println("현재점수 : " + score);
				break;
			case 2: // 아래
				printTile(moveTile(tile, SIZE * SIZE - 1, 1, 0));
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

	public static Tile[][] moveLeft(Tile[][] tile, int row, int col) {

		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile[i].length; j++) {

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
		}

		/*
		 * for (int i = 0; i < tile.length; i++) { for (int j = 0; j <
		 * tile[i].length; j++) {
		 * 
		 * if (tile[i][j].getNum() != 0) { if (j == 0) {
		 * 
		 * } else {
		 * 
		 * for (int k = 0; k < SIZE; k++) { for (int l = 0; l < j; l++) { if
		 * ((tile[i][j].getNum() == tile[i][l].getNum()) || (tile[i][j].getNum()
		 * != 0 && tile[i][l].getNum() == 0)) { System.out.println("i:" + i +
		 * ", j:" + j + ", k:" + k + ", l:");
		 * tile[i][l].setNum(tile[i][j].getNum() + tile[i][l].getNum());
		 * tile[i][j].setNum(0); break; } } }
		 * 
		 * }
		 * 
		 * } } }
		 */
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
								if ((tile[i][j].getNum() == tile[l][j].getNum())
										|| ((tile[l][j].getNum() == 0) && (tile[i][j].getNum() != 0))) {
									tile[l][j].setNum(tile[i][j].getNum() + tile[l][j].getNum());
									tile[i][j].setNum(0);
									break;
								}
							}
						}

						/*
						 * tile[0][j].setNum(tile[i][j].getNum() +
						 * tile[0][j].getNum()); tile[i][j].setNum(0);
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
		for (int i = tile.length - 1; i >= 0; i--) {
			for (int j = tile[i].length - 1; j >= 0; j--) {
				if (tile[j][i].getNum() != 0) {

					for (int l = SIZE - 1; l >= 0; l--) {
						for (int k = SIZE - 1; k > i; k--) {
							if ((tile[j][k].getNum() == tile[j][k - 1].getNum())
									|| (tile[j][k].getNum() != 0) && (tile[j][k - 1].getNum() == 0)
									|| (tile[j][k].getNum() == 0) && (tile[j][k - 1].getNum() != 0)) {
								System.out.println("j:" + j + ", i:" + i + ", k:" + k + ", i:" + i);
								tile[j][k].setNum(tile[j][k].getNum() + tile[j][k - 1].getNum());
								tile[j][k - 1].setNum(0);
							}
						}
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