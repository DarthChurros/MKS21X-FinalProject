# MKS21X-FinalProject
DAILY LOG:
Jan 3rd, 2018:
We created the repo today and also began two branches, one called ClassStructure by Tejas
The other is mainMethod by Alma. Tomorrow we plan to refine these branches and begin
Caesar cipher.
-wiifit

Jan 6, 2018:
The class structure for the ciphers is laid out as far as necessary. Abstract classes are
complete - branch ClassStructure has been merged. Will take out a branch soon to work
on a random simple substitution cipher. Caesar cipher will follow.
ALMA: finished the mainMethod and switched all if/then statements to switch statements
Further clarified class structure
-wiifit

Jan 7, 2018:
We realized errors in out class structure in class, so we are adding constructors
to each abstract class and then will start writing the actual methods

NIGHT UPDATE: started substitution cipher, having trouble with random jumbling of letters but shiftKey works fine --alma

Jan 8, 2018:
Tejas fixes super conflicts
Alma wrote substitutioncipher and caesar cipher, will have to adjust them according to new class structure

Jan 9, 2018:
Tejas beings on vigenere cipher
alma adjust substitution and caesar

jan 10, 2018:
tejas finishes vigenere
alma begins bookcipher, runtime very very slow. has to look into using string instead of arraLyist to hold data perhas
or for quicker way to scan through very long list of chars. difficult

jan 11, 2018:
alma fixes slow runtime for bookcipher. The problem was actually how the ciphertext
was being processed and there was an infinite loop as adding '-1' meant 1 kept being
processed over and over

jan 12, 2018:
tejas fixes several fatal bugs in vigenere. tested the demo file,
and it works so far. plan to add more customizability later in the day.
alma wrote railFencEcipher. was hard

jan 13, 2018:
alma adds the remaining ciphers in the set to the demo. adds method processText()
(static) to Cipher.java so to process sentences with spaces and punctuation.
adds BookCipher
tejas adds autokeycipher

jan 14, 2018:
tejas starts working to add more flexibility to the demo file. Support for
multiple ciphers. gave ciphers toStrings to display while running the demo.
Ciphers should have a method keyless() that attempts to decrypt the given
ciphertext without a key, but don't make that an abstract method as we won't
be able to implement it for all ciphers!

jan 15 2018:
big note: a linux-specific bug: bookcipher can't process the name of the text file bc linux is case sensitive
mac isn't. so processing the keyword input on mac is no issue, doing so on linux means you can only
use text files w titles in all-caps

jan 16, 2018:
alma -- spent a lot of time trying to rewrite keylessDecrypt for caesarCipher in a 
way that can decrypt encrypted text that has no spaces. might work and might not work. will check tomorrow
tejas -- editing alma's edits to demo so that the user can choose keyless decryption
before having instantiated an instance of a certain cipher

jan 17, 2018:
FINALLY GOT KEYLESSDECRYPT FOR CAESARCIPHER TO WORK MOST OF THE TIME!!!!!
Still should go ahead and package what's written there are a method that can be used in subCipher, just 
more times (since tejas needs to do letter frequency analysis; i just need to cycle through 26 possible keys)

