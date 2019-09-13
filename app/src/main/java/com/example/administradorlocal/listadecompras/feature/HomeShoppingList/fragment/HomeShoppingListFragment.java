package com.example.administradorlocal.listadecompras.feature.HomeShoppingList.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.feature.HomeShoppingList.adapter.ShowProductListAdapter;
import com.example.administradorlocal.listadecompras.persistence.entity.ShoppingList;
import com.example.administradorlocal.listadecompras.feature.main.main_container;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeShoppingListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeShoppingListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeShoppingListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Toolbar toolbar;
    private ListView lv;
    private View view;
    private List<ShoppingList> productList;
    private ShowProductListAdapter productListAdapter;

    public HomeShoppingListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeShoppingListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeShoppingListFragment newInstance(String param1, String param2) {
        HomeShoppingListFragment fragment = new HomeShoppingListFragment();
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
        this.view = inflater.inflate(R.layout.fragment_home_shopping_list, container, false);

        lv = this.view.findViewById(R.id.lv_showListProduct);
        this.productList = main_container.db.ShoppingListDao().findAll();
        productListAdapter = new ShowProductListAdapter(productList, this.getContext());
        toolbar = this.view.findViewById(R.id.toolbar);

        toolbarConfig();

        return this.view;
    }

    private void toolbarConfig() {
        lv.setAdapter(productListAdapter);
        if (productList.size() > 0) {
            toolbar.setTitle("lista de compra: " + DateFormat.format("dd/MM/yyyy", new Date(productList.get(0).getDate())).toString());
        } else {
            toolbar.setTitle("crie sua lista de compra");
        }
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
