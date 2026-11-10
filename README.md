# TOVA Attention Test (Java)

A Java recreation of the **Test of Variables of Attention (T.O.V.A.)**, a computer-based
test used in research and clinical settings to measure attention. The official test is
often used as one part of ADHD evaluations.

## How the test works

A square flashes on screen for 100 ms every 2 seconds. The participant presses a key
**only** when the square appears in the **top** position (the target) and holds back when
it appears at the bottom.

The test runs 4 quarters of 162 trials each, about 22 minutes in total:

- **Quarters 1–2:** targets are rare (36 of 162). This half tests **sustained attention**.
- **Quarters 3–4:** targets are common (126 of 162). This half tests **impulse control**.

## What it measures

| Measure | What it tells you |
|---------|-------------------|
| **RT**  | Average reaction time to targets (ms) |
| **RTV** | How consistent reaction times are (standard deviation, ms) |
| **Omissions** | % of targets missed (inattention) |
| **Commissions** | % of non-targets pressed (impulsivity) |
| **d′**  | How well targets are told apart from non-targets (signal detection) |

Scores are saved for each quarter, each half, and the whole test, along with every
recorded response, to `Documents/TOVA_data/<participant ID>.txt`.

## Running it

Requires Java 11 or newer.

```bash
cd src
javac *.java
java Main
```

Enter a participant ID in the format `ID_Group_Age_Gender_Test`, then click **Begin Test**.

## Files

- `Main.java` sets up the data folder and launches the app.
- `MyFrame.java` is the window and stimulus display.
- `UIDriver.java` runs the trial timing, records key presses, and calculates the scores.
- `ReactionInstance.java` stores a single response.

## Note

This is a personal project for learning and research practice. It is not the official
T.O.V.A. and is not meant for diagnosis.
