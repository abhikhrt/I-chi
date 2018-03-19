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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Maindry.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Maindry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Maindry extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText mndryet;
    private Button btndrys;
    String datess;

    public DatabaseHelper mydb;
    private OnFragmentInteractionListener mListener;

    public Maindry() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Maindry.
     */
    // TODO: Rename and change types and number of parameters
    public static Maindry newInstance(String param1, String param2) {
        Maindry fragment = new Maindry();
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_maindry, container, false);

        mndryet=(EditText)v.findViewById(R.id.etdrymn);
        btndrys=(Button)v.findViewById(R.id.btnmndry);
        mydb = new DatabaseHelper(getContext());
        btndrys.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        datess = bundle.getString("dates",null);

        Cursor res = mydb.getDryData(datess);
        if (res.getCount()==0)
            mndryet.setText("");
        else
            {
                res.moveToFirst();
                mndryet.setText(res.getString(1));
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

        boolean isInserted = mydb.insert_dry(datess,mndryet.getText().toString());
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
