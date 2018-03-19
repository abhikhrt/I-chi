package com.ontro.abhi.diary1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Note.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Note#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Note extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText mnntet1,mnntet2,mnntet3,mnntet4,mnntet5;
    private Button btnnts;
    String datess;

    public DatabaseHelper mydb;

    private OnFragmentInteractionListener mListener;

    public Note() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Note.
     */
    // TODO: Rename and change types and number of parameters
    public static Note newInstance(String param1, String param2) {
        Note fragment = new Note();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) throws IllegalStateException {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_note, container, false);

        mnntet1=(EditText)v.findViewById(R.id.etnt1);
        mnntet2=(EditText)v.findViewById(R.id.etnt2);
        mnntet3=(EditText)v.findViewById(R.id.etnt3);
        mnntet4=(EditText)v.findViewById(R.id.etnt4);
        mnntet5=(EditText)v.findViewById(R.id.etnt5);
        btnnts=(Button)v.findViewById(R.id.btnnt);
        mydb = new DatabaseHelper(getContext());
        btnnts.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        datess = bundle.getString("dates",null);


        Cursor res = mydb.getnotes(datess);
        if (res.getCount()==0) {
            mnntet1.setText("");
            mnntet2.setText("");
            mnntet3.setText("");
            mnntet4.setText("");
            mnntet5.setText("");
        }
        else
        {
            res.moveToFirst();

            mnntet1.setText(res.getString(1));
            mnntet2.setText(res.getString(2));
            mnntet3.setText(res.getString(3));
            mnntet4.setText(res.getString(4));
            mnntet5.setText(res.getString(5));
           // mnntet.setText(res.getString(2));
        }

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        boolean isInserted = mydb.insert_note(datess,mnntet1.getText().toString(),mnntet2.getText().toString(),mnntet3.getText().toString(),mnntet4.getText().toString(),mnntet5.getText().toString());
        if (isInserted==true)
        {
            Toast.makeText(getActivity(),"Successfully saved",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getActivity(),"Failed to save",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), choose_date1.class));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
