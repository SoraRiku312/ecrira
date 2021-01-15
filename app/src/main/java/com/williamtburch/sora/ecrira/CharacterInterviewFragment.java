package com.williamtburch.sora.ecrira;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterInterviewFragment extends Fragment {

    private static final String ARG_CHARACTER_ID = "character_id";

    private RecyclerView mInterviewRecyclerView;
    private InterviewAdapter mAdapter;
    private Character mCharacter;

    public static CharacterInterviewFragment newInstance(UUID characterId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHARACTER_ID, characterId);
        CharacterInterviewFragment fragment = new CharacterInterviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Character getCharacter(){
        return mCharacter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID characterId = (UUID)getArguments().getSerializable(ARG_CHARACTER_ID);
        mCharacter = CharacterLab.get(getActivity()).getCharacter(characterId);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_interview, container,
                false);
        mInterviewRecyclerView = (RecyclerView) view.findViewById(R.id.character_interview_recycler_view);
        mInterviewRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

/*
        Interview i1 = new Interview(1, "Hello?", "World!");
        Interview i2 = new Interview(2, "Ping?", "Pong!");

        mCharacter.editInterview(1, i1);
        mCharacter.editInterview(2, i2);
*/
//todo check why this if statement isnt workign correcly
        if(mCharacter.isNewForInterviews()){

            mCharacter.editInterview(0, "Where and when were you born?", "Answer");
            mCharacter.editInterview(1, "Who are/were your parents?", "Answer");
            mCharacter.editInterview(2, "Do you have any siblings? What are/were they like?", "Answer");
            mCharacter.addInterview(mCharacter.getInterviews().size(), "Which social class do you belong in?", "Answer");


            mCharacter.setNewForInterviews(false);


        }


        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Override
    public void onPause(){
        super.onPause();
        CharacterLab.get(getActivity()).updateCharacterInterview(mCharacter);
        CharacterLab.get(getActivity()).updateCharacterIsNewForInterview(mCharacter);
  //      CharacterLab.get(getActivity()).updateCharacterBasics(mCharacter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void updateUI(){
        List<Interview> interviews = mCharacter.getInterviews();
        if(mAdapter == null){
            mAdapter = new InterviewAdapter(interviews);
            mInterviewRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.setInterviews(interviews);
            mAdapter.notifyDataSetChanged();
        }
    }
//t/odo FINISH INTERVIEW SHIT, I GUESS MERGE INTERVIEW DATABNASE WITH CHARACTER DATABASE S
    private class InterviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Interview mInterview;
        private Character mCharacter;

        private TextView mQuestionTextView;
        private TextInputEditText mAnswerEditText;

        private String mQuestion;
        private String mAnswer;

        public InterviewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_interview, parent, false));
            itemView.setOnClickListener(this);
            mQuestionTextView = (TextView) itemView.findViewById(R.id.question_text_view);
            mAnswerEditText = (TextInputEditText) itemView.findViewById(R.id.answer_edit_text);
        }

        public void bind(Interview interview){
            mInterview = interview;

            mQuestion = mInterview.getQuestion();
            mAnswer = mInterview.getAnswer();




            //todo make editing this thing change the values in the character's interview, maybe add a setInterview function in the Character class that gets a current interview and changes the values


            mQuestionTextView.setText(mQuestion);
            mAnswerEditText.setText(mAnswer);


            mAnswerEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //
                }

                @Override
                public void afterTextChanged(Editable s) {
                  mInterview.setAnswer(s.toString());
                }
            });
        }

        @Override
        public void onClick(View view){
            //
        }








    }

    private class InterviewAdapter extends RecyclerView.Adapter<InterviewHolder>{


        private List<Interview> mInterviews;

        public InterviewAdapter(List<Interview> interviews){
            mInterviews = interviews;
        }

        @Override
        public InterviewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new InterviewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(InterviewHolder holder, int position){
            Interview interview = mInterviews.get(position);
            holder.bind(interview);
        }

        @Override
        public int getItemCount(){
            return mInterviews.size();
        }

        public void setInterviews(List <Interview> interviews){
            mInterviews = interviews;
        }
    }

}

