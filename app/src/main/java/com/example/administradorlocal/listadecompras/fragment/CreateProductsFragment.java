package com.example.administradorlocal.listadecompras.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.adapter.ProductListAdapter;
import com.example.administradorlocal.listadecompras.controller.CtrlFragment;
import com.example.administradorlocal.listadecompras.controller.IFragment;
import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.entity.ShoppingList;
import com.example.administradorlocal.listadecompras.views.shopList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProductsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private List<Product> productList;
    private View view;
    private ProductListAdapter productListAdapter;
    private IFragment ctrlFragment;

    public CreateProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateProductsFragment newInstance(String param1, String param2) {
        CreateProductsFragment fragment = new CreateProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_create_products, container, false);


        this.ctrlFragment = new CtrlFragment();
        this.productList = shopList.db.productDao().findAll();
        ListView lv = this.view.findViewById(R.id.listView);
        productListAdapter = new ProductListAdapter(productList, this.getContext());

        lv.setAdapter(productListAdapter);
        Toolbar toolbar = this.view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // Inflate the layout for this fragment
        return this.view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        List<Product> products = productListAdapter.getProductsChecked();

        switch (item.getItemId()) {

            case R.id.i_createList:
                // Do onlick on menu action here
                createList(products);
                return true;

            case R.id.i_delete_Products:
                deleteAllProducts(products);
                ctrlFragment.goToFragment(new CreateProductsFragment(), this.getFragmentManager());
                Toast.makeText(getContext(), "deletado com sucesso!!", Toast.LENGTH_LONG).show();
                return true;
        }
        return false;
    }

    private void createList(List<Product> p) {
        for (Product pr : p) {
            /*shopList.db.ShoppingListDao().insertListProduct(new ShoppingList(pr.getName(), pr.getImage(), new Date()));*/
            Date data = new Date();
        }

    }

    private void deleteAllProducts(List<Product> p) {
        for (Product x : p) {
            shopList.db.productDao().delete(x);
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
