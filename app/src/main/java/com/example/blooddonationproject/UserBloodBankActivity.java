package com.example.blooddonationproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserBloodBankActivity extends AppCompatActivity {

    ListView listView;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_blood_bank);





        listView = findViewById(R.id.listViewId);
        searchView = findViewById(R.id.searchViewId);






        String[] listNames ={"\n\nBangladesh Red Crescent Blood Bank"+"\n\nAddress:7/5, Aurangajeb Rd, Dhaka"+"\n\nOpen 24 hours"+"\n\nPhone:02-9139940"+"\n\n",
                "\n\nCrescent Blood Bank"+"\n\nAddress:Dorgah Moholla Rd, Sylhet 3100"+"\n\nOpen 24 hours"+"\n\nPhone:01937-591604"+"\n\n",
                "\n\nBangladesh Blood Bank and Transfusion Center" +"\n\nAddress:12,,22 Babar Rd, Dhaka"+"\n\nOpen 24 hours"+"\n\nPhone:01718-176854"+"\n\n",
                "\n\nFatema Begum Red Crescent Blood Center" +"\n\nAddress:395 Kata Pahar Ln, Chittagong"+"\n\nOpen 24 hours"+"\n\nPhone:031-612395"+"\n\n",
                "\n\nThalassemia Blood Bank"+"\n\nAddress:30 ChameliBag 1st Lane, Dhaka 1217"+"\n\nOpen:9am-5am, Close: Sat & Sun"+"\n\nPhone:02-8332481"+"\n\n",
                "\n\nGreen Life Blood Bank"+"\n\nAddress:22, Osmani, Medical College Rd, Sylhet"+"\n\nOpen 24 hours"+"\n\nPhone:01821-716397"+"\n\n",
                "\n\nMukti Blood Bank & Pathology Lab"+"\n\nAddress:54(1st Floor),Bir-Uttuam A.M. ShafiUllah Rd,Free School St, Dhaka 1205"+"\n\nOpen 24 hours"+"\n\nPhone:02-624249"+"\n\n",
                "\n\nBadhan"+"\n\nAddress:R C Mojumder Auditorium, TSC, Secretariate Rd, Dhaka 1000"+"\n\nOpen 6am-9am"+"\n\nPhone:01534-982674"+"\n\n",
                "\n\nBlood Bank, Rajshahi Medical College"+"\n\nRajshahi Medical College"+"\n\nOpen 24 hours"+"\n\nPhone:01705415298"+"\n\n",
                "\n\nQuantam Lab"+"\n\nAddress:31/V Shilpacharya Zainul Abedin Sarak, Dhaka 1217"+"\n\nOpen 24 hours"+"\n\nPhone:02-9351969"+"\n\n",
                "\n\nBangladesh Online Blood Bank"+"\n\nAddress:Chittagong"+"\n\nOpen:10am to 5pm,Close: Sat & Sun "+"\n\nPhone:01913-919597"+"\n\n",
                "\n\nRed Crecent Bhaban"+"\n\nAddress:61 Motijheel Rd Dhaka 1100"+"\n\nOpen:7am-6am, Close: Friday"+"\n\nPhone: Not Available"+"\n\n",
                "\n\nBloodinfobd.com"+"\n\nAddress:Shahjadpur Bus Stand,Dhaka"+"\n\nOpen:9am-11am"+"\n\nPhone:01709-848480"+"\n\n",
                "\n\nSandhani Donar Club"+"\n\nAddress:Shib Bari More Cir, Khulna"+"\n\nOpen:9am-2am, Close: Friday"+"\n\nPhone:01911-83694"+"\n\n",
                "\n\nBadhan Transfusion Center(BTC)"+"\n\nAddress:Dhaka University Campus, Dhaka, Bangladesh"+"\n\nOpen 24 hours"+"\n\nPhone:02-58612545"+"\n\n",
                "\n\nJEMISON BLOOD BANK"+"\n\nAddress:37 Raja Pukur Lainn, Chittagong 4100"+"\n\nOpen 24 hours"+"\n\nPhone:Not Available"+"\n\n",
                "\n\nIslami Bank Hospital Mirpur"+"\n\nAddress:Purobi Bus Stop, Begum Rokeya Avenue, Dhaka 1216"+"\n\nOpen 24 hours"+"\n\nPhone:01992-346631"+"\n\n",
                "\n\nSBMC"+"\n\nAddress:Sagardi, Barisal, Bangladesh"+"\n\nOpen 24 hours"+"\n\nPhone:Not Available"+"\n\n",
                "\n\nBlood Donation"+"\n\nAddress:Hill View 2no road, Block C, Jamal Building, Chittagong 4209"+"\n\nOpen 24 hours"+"\n\nPhone:01815-000123"+"\n\n"
        };



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listNames){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View myView = super.getView(position, convertView, parent);

                TextView textView1 = myView.findViewById(android.R.id.text1);
                TextView textView2 = myView.findViewById(android.R.id.text2);
                TextView textView3 = myView.findViewById(android.R.id.text1);
                TextView textView4 = myView.findViewById(android.R.id.text1);



                return myView;
            }
        };


        listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String items = listView.getItemAtPosition(i).toString();

                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + items));// Initiates the Intent
                callIntent.putExtra("phone",items);
                startActivity(callIntent);



            }
        });

         */





    }
}