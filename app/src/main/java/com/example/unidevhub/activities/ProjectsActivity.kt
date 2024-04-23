package com.example.unidevhub.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.unidevhub.R
import com.example.unidevhub.adapters.CategoryAdapter
import com.example.unidevhub.adapters.ShuffleAdapter
import com.example.unidevhub.databinding.ActivityProjectsBinding
import com.example.unidevhub.models.Category



class ProjectsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProjectsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()

//        var mCodeView  = binding.codeView;

        binding.codeView.fontSize=14f
        binding.codeView.code="import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "class Account {\n" +
                "    private int accountId;\n" +
                "    private double balance;\n" +
                "\n" +
                "    public Account(int accountId, double initialBalance) {\n" +
                "        this.accountId = accountId;\n" +
                "        this.balance = initialBalance;\n" +
                "    }\n" +
                "\n" +
                "    public int getAccountId() {\n" +
                "        return accountId;\n" +
                "    }\n" +
                "\n" +
                "    public double getBalance() {\n" +
                "        return balance;\n" +
                "    }\n" +
                "\n" +
                "    public void deposit(double amount) {\n" +
                "        balance += amount;\n" +
                "    }\n" +
                "\n" +
                "    public boolean withdraw(double amount) {\n" +
                "        if (balance >= amount) {\n" +
                "            balance -= amount;\n" +
                "            return true;\n" +
                "        } else {\n" +
                "            System.out.println(\"Insufficient funds\");\n" +
                "            return false;\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Customer {\n" +
                "    private int customerId;\n" +
                "    private String name;\n" +
                "    private List<Account> accounts;\n" +
                "\n" +
                "    public Customer(int customerId, String name) {\n" +
                "        this.customerId = customerId;\n" +
                "        this.name = name;\n" +
                "        this.accounts = new ArrayList<>();\n" +
                "    }\n" +
                "\n" +
                "    public int getCustomerId() {\n" +
                "        return customerId;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    public void addAccount(Account account) {\n" +
                "        accounts.add(account);\n" +
                "    }\n" +
                "\n" +
                "    public List<Account> getAccounts() {\n" +
                "        return accounts;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Bank {\n" +
                "    private List<Customer> customers;\n" +
                "\n" +
                "    public Bank() {\n" +
                "        this.customers = new ArrayList<>();\n" +
                "    }\n" +
                "\n" +
                "    public void addCustomer(Customer customer) {\n" +
                "        customers.add(customer);\n" +
                "    }\n" +
                "\n" +
                "    public List<Customer> getCustomers() {\n" +
                "        return customers;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Bank bank = new Bank();\n" +
                "\n" +
                "        Customer customer1 = new Customer(1, \"Alice\");\n" +
                "        Account account1 = new Account(101, 1000);\n" +
                "        customer1.addAccount(account1);\n" +
                "        bank.addCustomer(customer1);\n" +
                "\n" +
                "        Customer customer2 = new Customer(2, \"Bob\");\n" +
                "        Account account2 = new Account(102, 2000);\n" +
                "        customer2.addAccount(account2);\n" +
                "        bank.addCustomer(customer2);\n" +
                "\n" +
                "        // Perform transactions\n" +
                "        customer1.getAccounts().get(0).deposit(500);\n" +
                "        customer2.getAccounts().get(0).withdraw(100);\n" +
                "\n" +
                "        // Display customer information\n" +
                "        for (Customer customer : bank.getCustomers()) {\n" +
                "            System.out.println(\"Customer: \" + customer.getName());\n" +
                "            for (Account account : customer.getAccounts()) {\n" +
                "                System.out.println(\"Account ID: \" + account.getAccountId() + \", Balance: \$\" + account.getBalance());\n" +
                "            }\n" +
                "            System.out.println();\n" +
                "        }\n" +
                "    }\n" +
                "}\n"

        binding.codeView.show()
        var projectName : String? = intent.getStringExtra("projectName")
        binding.textView5.text = projectName

//
//        val JAVA_CODE = "import java.util.ArrayList;\n" +
//                "import java.util.HashMap;\n" +
//                "import java.util.Map;\n" +
//                "\n" +
//                "public class Main {\n" +
//                "    public static void main(String[] args) {\n" +
//                "        ArrayList<Integer> list = new ArrayList<>();\n" +
//                "        list.add(1);\n" +
//                "        list.add(2);\n" +
//                "        list.add(3);\n" +
//                "        list.add(2);\n" +
//                "        list.add(2);\n" +
//                "        list.add(3);\n" +
//                "        list.add(4);\n" +
//                "        list.add(5);\n" +
//                "        list.add(5);\n" +
//                "        list.add(5);\n" +
//                "        list.add(5);\n" +
//                "\n" +
//                "        Map<Integer, Integer> frequencyMap = new HashMap<>();\n" +
//                "\n" +
//                "        // Count the frequency of each element\n" +
//                "        for (int num : list) {\n" +
//                "            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);\n" +
//                "        }\n" +
//                "\n" +
//                "        // Find the element with maximum frequency\n" +
//                "        int maxFrequency = 0;\n" +
//                "        int maxFreqElement = 0;\n" +
//                "\n" +
//                "        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {\n" +
//                "            if (entry.getValue() > maxFrequency) {\n" +
//                "                maxFrequency = entry.getValue();\n" +
//                "                maxFreqElement = entry.getKey();\n" +
//                "            }\n" +
//                "        }\n" +
//                "\n" +
//                "        System.out.println(\"Element with maximum frequency: \" + maxFreqElement + \" (Frequency: \" + maxFrequency + \")\");\n" +
//                "    }\n" +
//                "}\n"
//        mCodeView
//            .setTheme(Theme.AGATE)
//            .setCode(JAVA_CODE)
//            .setLanguage(Language.JAVA)
//            .setWrapLine(true)
//            .setFontSize(14F)
//            .setZoomEnabled(true)
//            .setShowLineNumber(true)
//            .setStartLineNumber(9000)
//            .apply();


    }

    private fun initRecyclerView() {
        val branches = listOf(
                Category(1,"Main"),
                Category(2, "Master")
            )

        val adapter = CategoryAdapter(this,branches )
        binding.catRecycler.layoutManager = LinearLayoutManager(this)
        binding.catRecycler.adapter = adapter
    }
}