package test;

import com.sun.source.tree.AssertTree;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpellCheckerTest {

	// A spellchecker should be able to tell us how many words it currently knows about
	@Test
	void testWordCount() {
		//1. Create the object to be tested
		SpellChecker cheker = new SpellChecker();

		//2 Call the method being tested
		int words = cheker.getNumberOfWords()

		//3. Use assertions to validate
		assertEquals(0, words);	
	}

	// The number of words a spellchecker knows about should go up by one whenever a new word is added
	@Test
	void testAddWord() {
		SpellChecker cheker = new SpellChecker();
		checker.addWord("cat");
        assertEquals(1, checker.getNumberOfWords());
	}

	// If a word is added that is already contained in the spellchecker, then the number of words contained in the spellchecker should not change
	@Test
	void testDuplicateWord() {
		SpellChecker cheker = new SpellChecker();
		checker.addWord("cat");
		checker.addWord("cat");
		assertEquals(1, checker.getNumberOfWords());
	}

	// A spellchecker should be able to accept a properly spelled word and return an indication that it is properly spelled
	@Test
	void testCheckWord() {	
		SpellChecker cheker = new SpellChecker();
		checker.addWord("cat");
		assertTrue(checker.checkWord("cat"));
	}

	// A spellchecker should be able to accept an improperly spelled word and return an indication that it is improperly spelled
	@Test
	void testCheckWordFalse() {	
		SpellChecker cheker = new SpellChecker();
		checker.addWord("cat");
		assertFalse(checker.checkWord("dog"));
	}

	// A spellchecker should ignore case when checking how words are spelled (i.e. "cat" and "CaT" are both properly spelled as long as "cat" (or "Cat" or "caT", etc.) appears in our spellchecker.
	@Test
	void testCheckWordCase() {	
		SpellChecker cheker = new SpellChecker();
		checker.addWord("cat");
		assertTrue(checker.checkWord("CaT"));
		assertTrue(checker.checkWord("caT"));
		AssertTrue(checker.checkWord("Cat"));
	}

	// A spellchecker should be able to recommend a properly spelled word for any String that it is given. In other words, if I were to input the String "bamk", the spell checker could suggest that the word "bank" is properly spelled, meaning that the word "bank" is the alphabetically closest word in the spellchecker to the given String "bamk"
	@Test
	void testRecommendWord() {		
		SpellChecker cheker = new SpellChecker();
		checker.addWord("bank");
		checker.addWord("cat");
		checker.addWord("dog");
		assertEquals("bank", checker.recommendWord("bamk"));
	}

	// If we ask the spellchecker to suggest a properly spelled word for a String that is already properly spelled, it should simply return the original word.
	@Test
	void testRecommendWordProperlySpelled() {		
		SpellChecker cheker = new SpellChecker();
		checker.addWord("bank");
		checker.addWord("cat");
		checker.addWord("dog");
		assertEquals("bank", checker.recommendWord("bank"));
	}

	// If we ask the spellchecker to suggest a properly spelled word for a String that is not properly spelled, but there are no words in the spellchecker that are similar to the given String, then the spellchecker should return null.
	@Test
	void testRecommendWordNoSimilarWords() {
		SpellChecker cheker = new SpellChecker();
		checker.addWord("bank");
		checker.addWord("cat");
		checker.addWord("dog");
		assertNull(checker.recommendWord("xyz"));
	}

	// If the input string is empty, suggest should return null.
	@Test
	void testRecommendWordEmptyString() {
		SpellChecker cheker = new SpellChecker();
		checker.addWord("bank");
		checker.addWord("cat");
		checker.addWord("dog");
		assertNull(checker.recommendWord(""));
	}
}

