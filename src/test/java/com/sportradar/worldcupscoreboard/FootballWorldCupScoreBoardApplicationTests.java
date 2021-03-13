package com.sportradar.worldcupscoreboard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.sportradar.worldcupscoreboard.controller.ScoresController;
import com.sportradar.worldcupscoreboard.entity.Scores;
import com.sportradar.worldcupscoreboard.model.StartGameIn;
import com.sportradar.worldcupscoreboard.model.UpdateScoreIn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FootballWorldCupScoreBoardApplicationTests {

	@Autowired
	private ScoresController scoresController;

	@Test
	/*
	 * Test 1: Search Results Live Matches
	 */
	public void testA() throws Exception {

		List<Scores> scoresResultList = scoresController.searchScoreLiveGames();
		assertThat(scoresResultList,
				contains(
						allOf(hasProperty("localTeam", is("Uruguay")), hasProperty("localTeamGoals", is(6)),
								hasProperty("awayTeam", is("Italy")), hasProperty("awayTeamGoals", is(6))),
						allOf(hasProperty("localTeam", is("Spain")), hasProperty("localTeamGoals", is(10)),
								hasProperty("awayTeam", is("Brazil")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Mexico")), hasProperty("localTeamGoals", is(0)),
								hasProperty("awayTeam", is("Canada")), hasProperty("awayTeamGoals", is(5))),
						allOf(hasProperty("localTeam", is("Argentina")), hasProperty("localTeamGoals", is(3)),
								hasProperty("awayTeam", is("Australia")), hasProperty("awayTeamGoals", is(1))),
						allOf(hasProperty("localTeam", is("Germany")), hasProperty("localTeamGoals", is(2)),
								hasProperty("awayTeam", is("France")), hasProperty("awayTeamGoals", is(2)))));
	}

	@Test
	/*
	 * Test 2: Add a new game
	 */
	public void testB() throws Exception {

		StartGameIn startGameIn = new StartGameIn("Belgium", "Ecuador");
		scoresController.startGame(startGameIn);

		List<Scores> scoresResultList = scoresController.searchScoreLiveGames();
		assertThat(scoresResultList,
				contains(
						allOf(hasProperty("localTeam", is("Uruguay")), hasProperty("localTeamGoals", is(6)),
								hasProperty("awayTeam", is("Italy")), hasProperty("awayTeamGoals", is(6))),
						allOf(hasProperty("localTeam", is("Spain")), hasProperty("localTeamGoals", is(10)),
								hasProperty("awayTeam", is("Brazil")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Mexico")), hasProperty("localTeamGoals", is(0)),
								hasProperty("awayTeam", is("Canada")), hasProperty("awayTeamGoals", is(5))),
						allOf(hasProperty("localTeam", is("Argentina")), hasProperty("localTeamGoals", is(3)),
								hasProperty("awayTeam", is("Australia")), hasProperty("awayTeamGoals", is(1))),
						allOf(hasProperty("localTeam", is("Germany")), hasProperty("localTeamGoals", is(2)),
								hasProperty("awayTeam", is("France")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Belgium")), hasProperty("localTeamGoals", is(0)),
								hasProperty("awayTeam", is("Ecuador")), hasProperty("awayTeamGoals", is(0)))));
	}

	@Test
	/*
	 * Test 3: Finish a game
	 */
	public void testC() throws Exception {

		scoresController.finishGame(Long.valueOf(1));

		List<Scores> scoresResultList = scoresController.searchScoreLiveGames();
		assertThat(scoresResultList,
				contains(
						allOf(hasProperty("localTeam", is("Uruguay")), hasProperty("localTeamGoals", is(6)),
								hasProperty("awayTeam", is("Italy")), hasProperty("awayTeamGoals", is(6))),
						allOf(hasProperty("localTeam", is("Spain")), hasProperty("localTeamGoals", is(10)),
								hasProperty("awayTeam", is("Brazil")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Argentina")), hasProperty("localTeamGoals", is(3)),
								hasProperty("awayTeam", is("Australia")), hasProperty("awayTeamGoals", is(1))),
						allOf(hasProperty("localTeam", is("Germany")), hasProperty("localTeamGoals", is(2)),
								hasProperty("awayTeam", is("France")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Belgium")), hasProperty("localTeamGoals", is(0)),
								hasProperty("awayTeam", is("Ecuador")), hasProperty("awayTeamGoals", is(0)))));
	}

	@Test
	/*
	 * Test 4: Update a score from a game
	 */
	public void testD() throws Exception {

		UpdateScoreIn updateScoreIn = new UpdateScoreIn(Long.valueOf(2), 20, 2);
		scoresController.updateScore(updateScoreIn);

		List<Scores> scoresResultList = scoresController.searchScoreLiveGames();
		assertThat(scoresResultList,
				contains(
						allOf(hasProperty("localTeam", is("Spain")), hasProperty("localTeamGoals", is(20)),
								hasProperty("awayTeam", is("Brazil")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Uruguay")), hasProperty("localTeamGoals", is(6)),
								hasProperty("awayTeam", is("Italy")), hasProperty("awayTeamGoals", is(6))),
						allOf(hasProperty("localTeam", is("Argentina")), hasProperty("localTeamGoals", is(3)),
								hasProperty("awayTeam", is("Australia")), hasProperty("awayTeamGoals", is(1))),
						allOf(hasProperty("localTeam", is("Germany")), hasProperty("localTeamGoals", is(2)),
								hasProperty("awayTeam", is("France")), hasProperty("awayTeamGoals", is(2))),
						allOf(hasProperty("localTeam", is("Belgium")), hasProperty("localTeamGoals", is(0)),
								hasProperty("awayTeam", is("Ecuador")), hasProperty("awayTeamGoals", is(0)))));
	}

	@Test
	/*
	 * Test 5: Finish all games except 1
	 */
	public void testE() throws Exception {

		scoresController.finishGame(Long.valueOf(2));
		scoresController.finishGame(Long.valueOf(3));
		scoresController.finishGame(Long.valueOf(4));
		scoresController.finishGame(Long.valueOf(5));
		scoresController.finishGame(Long.valueOf(6));

		List<Scores> scoresResultList = scoresController.searchScoreLiveGames();
		assertEquals(0, scoresResultList.size());
	}

	@Test
	/*
	 * Test 6: Start a game without teams
	 */
	public void testF() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			scoresController.startGame(null);
		});

		String expectedMessage = "startGame.startGameIn: Local and Away Team are required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	/*
	 * Test 7: Start a game without local team
	 */
	public void testG() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			StartGameIn startGameIn = new StartGameIn(null, "Venezuela");
			scoresController.startGame(startGameIn);
		});

		String expectedMessage = "startGame.startGameIn.localTeam: Local Team is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 8: Start a game without Awat team
	 */
	public void testH() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			StartGameIn startGameIn = new StartGameIn("Venezuela", null);
			scoresController.startGame(startGameIn);
		});

		String expectedMessage = "startGame.startGameIn.awayTeam: Away Team is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 9: finish a game wihtout a id
	 */
	public void testI() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			scoresController.finishGame(null);
		});

		String expectedMessage = "finishGame.idScore: Id Score is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 10: finish a not exist game
	 */
	public void testJ() throws Exception {

		Exception exception = assertThrows(Exception.class, () -> {

			scoresController.finishGame(Long.valueOf(999));
		});

		String expectedMessage = "The id Score doesn´t exist.";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 11: Update a null game
	 */
	public void testK() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			scoresController.updateScore(null);
		});

		String expectedMessage = "updateScore.updateScoreIn: Id Score, Local Team Goals and Away Team Goals are required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 12: Update a not existing game
	 */
	public void testL() throws Exception {

		Exception exception = assertThrows(Exception.class, () -> {

			scoresController.updateScore(new UpdateScoreIn(Long.valueOf(999), 10, 2));
		});

		String expectedMessage = "The id Score doesn´t exist.";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 13: Update a game without id
	 */
	public void testM() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			scoresController.updateScore(new UpdateScoreIn(null, 10, 2));
		});

		String expectedMessage = "updateScoreIn.idScore: Id Score is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 14: Update a game without local team goals
	 */
	public void testN() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			scoresController.updateScore(new UpdateScoreIn(Long.valueOf(2), null, 0));
		});

		String expectedMessage = "updateScoreIn.localTeamGoals: Local Team Goals is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 15: Update a game without away team goals
	 */
	public void testO() throws Exception {

		Exception exception = assertThrows(ConstraintViolationException.class, () -> {

			scoresController.updateScore(new UpdateScoreIn(Long.valueOf(2), 0, null));
		});

		String expectedMessage = "updateScoreIn.awayTeamGoals: Away Team Goals is required";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 16: Update a finished game
	 */
	public void testP() throws Exception {

		Exception exception = assertThrows(Exception.class, () -> {

			scoresController.updateScore(new UpdateScoreIn(Long.valueOf(2), 0, 0));
		});

		String expectedMessage = "This game is already finish";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 17: Finish a finished game
	 */
	public void testQ() throws Exception {

		Exception exception = assertThrows(Exception.class, () -> {

			scoresController.finishGame(Long.valueOf(1));
		});

		String expectedMessage = "This game is already finish";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	/*
	 * Test 18: Start the same country in two games.
	 */
	public void testR() throws Exception {

		StartGameIn startGameIn = new StartGameIn("Belgium", "Ecuador");
		scoresController.startGame(startGameIn);

		Exception exception = assertThrows(Exception.class, () -> {

			StartGameIn startGameBelgiumUsaIn = new StartGameIn("Belgium", "USA");
			scoresController.startGame(startGameBelgiumUsaIn);
		});

		String expectedMessage = "Some of the teams are playing now in other match";
    	String actualMessage = exception.getMessage();

    	assertTrue(actualMessage.contains(expectedMessage));
	}
}
