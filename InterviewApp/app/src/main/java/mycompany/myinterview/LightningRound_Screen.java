package mycompany.myinterview;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LightningRound_Screen extends Activity {

    LightningRoundQuestionManager lrQM;
    MediaPlayer player;
    int score;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lightning_round);

            lrQM = new LightningRoundQuestionManager();

            final TextView questionView = (TextView) findViewById(R.id.questionText);

            String question = lrQM.runRandom();
            questionView.setText(question);

            Button homeButton = (Button) findViewById(R.id.homeButton);
            Button restartButton = (Button) findViewById(R.id.restartButton);
            final Button nextButton = (Button) findViewById(R.id.nextButton);

            homeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    player.stop();
                    Intent intent = new Intent (LightningRound_Screen.this, Home.class);
                    startActivity(intent);
                }
            });

            restartButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    player.stop();
                    Intent intent = new Intent(LightningRound_Screen.this, LightningRound_Screen.class);
                    startActivity(intent);
                }
            });

            nextButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String question = lrQM.runRandom();
                    questionView.setText(question);
                    score = score + 1;
                }
            });

            player = MediaPlayer.create(LightningRound_Screen.this, R.raw.wbftanthem);

            player.start();

            new CountDownTimer(20000, 1000) {

                TextView mTextField = (TextView) findViewById(R.id.mTextField);

                public void onTick(long millisUntilFinished) {
                    mTextField.setText("Time remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    mTextField.setText("done!");
                    questionView.setText("Score: " + score);
                    nextButton.setEnabled(false);
                }
            }.start();


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_question__screen, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
