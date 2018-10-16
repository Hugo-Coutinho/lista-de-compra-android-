package com.example.administradorlocal.listadecompras.fragment;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.adapter.ProductListAdapter;
import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.views.shopList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> imgProductInt;
    private List<Bitmap> imgList;
    private List<String> nameProductList;
    private View view;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_create_products, container, false);


        this.productList = shopList.db.productDao().findAll();
        ListView lv = (ListView) this.view.findViewById(R.id.listView);
        lv.setAdapter(new ProductListAdapter(productList, this.getContext()));

        /*File directory = context.getFilesDir();
        File file = new File(directory, filename);*/





        /*this.imgList = getImage(productList);
        preparingListImage(this.imgList);*/

        // Inflate the layout for this fragment
        return this.view;
    }


    /*private void preparingListImage(List<Bitmap> image) {

        for (int a = 0; a <= 3; a++) {
            *//*Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image.get(a));*//*
            int width = image.get(a).getWidth();
            int height = image.get(a).getHeight();
            int[] store = new int[width * height];
            image.get(a).getPixels(store, 0, width, 0, 0, width, height);
        }
    }

    public List<Bitmap> getImage(List<Product> p) {
        List<Bitmap> zin = new ArrayList<>();
        Bitmap zao;

        for (Product x : p) {
            zao = getImageConverted(x.getId());

            zin.add(zao);
        }
        return zin;
    }


    public Bitmap getImageConverted(int i) {

        String qu = "select image from product where id=" + i;
        Cursor cur = shopList.db.query(qu, null);

        if (cur.moveToFirst()) {
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;
    }

*/
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
