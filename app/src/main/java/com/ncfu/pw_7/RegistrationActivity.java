package com.ncfu.pw_7;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etFio;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etBirthDate;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private Spinner spinnerGender;
    private CheckBox cbAgree;
    private Button btnRegister;

    private final Calendar birthCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFio = findViewById(R.id.etFio);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etBirthDate = findViewById(R.id.etBirthDate);
        etPassword = findViewById(R.id.etPassword);
        etRepeatPassword = findViewById(R.id.etRepeatPassword);
        spinnerGender = findViewById(R.id.spinnerGender);
        cbAgree = findViewById(R.id.cbAgree);
        btnRegister = findViewById(R.id.btnRegister);

        setupSpinner();
        setupDatePicker();

        btnRegister.setOnClickListener(v -> checkForm());
    }

    private void setupSpinner() {
        String[] genders = {
                "Выберите пол",
                "Мужской",
                "Женский"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                genders
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
    }

    private void setupDatePicker() {
        etBirthDate.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RegistrationActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        birthCalendar.set(year, month, dayOfMonth);

                        String date = String.format(
                                "%02d.%02d.%04d",
                                dayOfMonth,
                                month + 1,
                                year
                        );

                        etBirthDate.setText(date);
                        etBirthDate.setError(null);
                    },
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );

            Calendar minDate = Calendar.getInstance();
            minDate.set(1900, Calendar.JANUARY, 1);

            datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(now.getTimeInMillis());

            datePickerDialog.show();
        });
    }

    private void checkForm() {
        String fio = etFio.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String birthDate = etBirthDate.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String repeatPassword = etRepeatPassword.getText().toString().trim();

        boolean isValid = true;

        if (TextUtils.isEmpty(fio)) {
            etFio.setError("Введите ФИО");
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Введите email");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Введите корректный email");
            isValid = false;
        }

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError("Введите телефон");
            isValid = false;
        }

        if (TextUtils.isEmpty(birthDate)) {
            etBirthDate.setError("Выберите дату рождения");
            isValid = false;
        }

        if (spinnerGender.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Выберите пол", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Введите пароль");
            isValid = false;
        } else if (password.length() < 6) {
            etPassword.setError("Пароль должен быть не короче 6 символов");
            isValid = false;
        }

        if (TextUtils.isEmpty(repeatPassword)) {
            etRepeatPassword.setError("Повторите пароль");
            isValid = false;
        } else if (!password.equals(repeatPassword)) {
            etRepeatPassword.setError("Пароли не совпадают");
            isValid = false;
        }

        if (!cbAgree.isChecked()) {
            cbAgree.setError("Необходимо согласие");
            Toast.makeText(this, "Необходимо согласиться с условиями", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else {
            cbAgree.setError(null);
        }

        if (isValid) {
            Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Исправьте ошибки в форме", Toast.LENGTH_SHORT).show();
        }
    }
}