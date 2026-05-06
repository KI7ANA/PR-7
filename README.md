<div align="center">

# Отчёт

</div>

<div align="center">

## Практическая работа №7

</div>

<div align="center">

## Локализация и списки. Формы ввода и валидация данных

</div>

**Выполнил:**  
Ткачев Сергей Юрьевич  
**Курс:** 2  
**Группа:** ИНС-б-о-24-2  
**Направление:** ИПИНЖ (Институт перспективной инженерии)  
**Профиль:** Информационные системы и технологии  

---

### Цель работы

Изучить механизмы локализации Android-приложений, научиться работать со списками `ListView` и `Spinner`, освоить различные типы полей ввода и реализовать валидацию пользовательского ввода в Android-приложении на языке Java.

---

### Ход работы

#### Задание 1: Создание проекта и локализация

1. Был открыт Android Studio и создан новый проект с шаблоном **Empty Views Activity**.
2. Проекту было дано имя `PW_7`.
3. В качестве языка программирования был выбран **Java**.
4. Package name проекта был задан как:

```text
com.ncfu.pw_7
```

5. В файле `res/values/strings.xml` были определены строки для русской локализации приложения.
6. Для английской локализации был создан файл `res/values-en/strings.xml`.
7. В строковые ресурсы был добавлен массив `groupmates_array`, который используется для отображения списка одногруппников.

##### `res/values/strings.xml`

```xml
<resources>
    <string name="app_name">PW_7</string>
    <string name="groupmates_title">Список одногруппников</string>
    <string name="registration">Registration</string>

    <string-array name="groupmates_array">
        <item>Анагурбанов Байрам</item>
        <item>Арустамов Артур</item>
        <item>Атаян Ливон</item>
        <item>Айбазов Умар</item>
        <item>Баркетов Родион</item>
        <item>Болонина Валерия</item>
        <item>Братов Амир</item>
        <item>Волуйский Даниил</item>
        <item>Жигуров Руслан</item>
        <item>Золотов Владислав</item>
        <item>Измайлов Руслан</item>
        <item>Ковалёв Дмитрий</item>
        <item>Скирневский Денис</item>
        <item>Тристан Владислава</item>
        <item>Ткачев Сергей</item>
        <item>Ткаченко Денис</item>
        <item>Нашев Айдемир</item>
        <item>Майстренко Константин</item>
        <item>Макаров Мирон</item>
        <item>Мусаев Владислав</item>
        <item>Фёдоров Артём</item>
        <item>Хубиев Шатбек</item>
    </string-array>
</resources>
```

##### `res/values-en/strings.xml`

```xml
<resources>
    <string name="app_name">PW_7</string>
    <string name="groupmates_title">Groupmates list</string>
    <string name="registration">Registration</string>

    <string-array name="groupmates_array">
        <item>Anagurbanov Bayram</item>
        <item>Arustamov Artur</item>
        <item>Atayan Livon</item>
        <item>Aybazov Umar</item>
        <item>Barketov Rodion</item>
        <item>Bolonina Valeria</item>
        <item>Bratov Amir</item>
        <item>Voluysky Daniil</item>
        <item>Zhigurov Ruslan</item>
        <item>Zolotov Vladislav</item>
        <item>Izmailov Ruslan</item>
        <item>Kovalev Dmitry</item>
        <item>Skirnevsky Denis</item>
        <item>Tristan Vladislava</item>
        <item>Tkachev Sergey</item>
        <item>Tkachenko Denis</item>
        <item>Nashev Aydemir</item>
        <item>Maistrenko Konstantin</item>
        <item>Makarov Miron</item>
        <item>Musaev Vladislav</item>
        <item>Fedorov Artem</item>
        <item>Khubiev Shatbek</item>
    </string-array>
</resources>
```

<div align="center">

<img width="380" height="790" alt="image" src="https://github.com/user-attachments/assets/0b99e760-8f4a-4a9b-a989-5e5c2879fa99" />

*Рисунок 1. Русская локализация приложения*

</div>

<div align="center">

<img width="384" height="784" alt="image" src="https://github.com/user-attachments/assets/31765ec1-fd73-4aa2-9155-dcc7ca728d6b" />

*Рисунок 2. Английская локализация приложения*

</div>

---

#### Задание 2: Работа со списком ListView

1. В файле `activity_main.xml` был создан главный экран приложения.
2. На экран были добавлены:
- `TextView` для отображения заголовка списка;
- `ListView` для вывода списка одногруппников;
- `Button` для перехода на экран регистрации.
3. В `MainActivity.java` был получен массив строк из ресурсов.
4. Для отображения списка был создан `ArrayAdapter`.
5. Для `ListView` был добавлен обработчик нажатия, который выводит `Toast` с выбранным элементом списка.

##### `res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/groupmates_title"
        android:textSize="24sp"
        android:textStyle="bold"
        android:gravity="center"
        android:layout_marginBottom="16dp" />

    <ListView
        android:id="@+id/listViewGroupmates"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

    <Button
        android:id="@+id/btnRegistration"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/registration" />

</LinearLayout>
```

##### `MainActivity.java`

```java
package com.ncfu.pw_7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listViewGroupmates;
    private Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewGroupmates = findViewById(R.id.listViewGroupmates);
        btnRegistration = findViewById(R.id.btnRegistration);

        String[] groupmates = getResources().getStringArray(R.array.groupmates_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                groupmates
        );

        listViewGroupmates.setAdapter(adapter);

        listViewGroupmates.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = groupmates[position];
            Toast.makeText(this, selectedName, Toast.LENGTH_SHORT).show();
        });

        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
```

<div align="center">

<img width="386" height="786" alt="image" src="https://github.com/user-attachments/assets/633c1d23-33d4-4c5e-9e4d-a7f1356e46fe" />

*Рисунок 3. Главный экран приложения со списком одногруппников*

</div>

<div align="center">

<img width="383" height="791" alt="image" src="https://github.com/user-attachments/assets/38bf0486-0c8a-4954-9042-1bcf5df3a30a" />

*Рисунок 4. Вывод Toast при выборе элемента списка*

</div>

---

#### Задание 3: Создание формы регистрации

1. В проект была добавлена новая Activity с именем `RegistrationActivity`.
2. Активность была добавлена в файл `AndroidManifest.xml`.
3. В файле `activity_registration.xml` была создана форма регистрации.
4. На форму были добавлены следующие элементы:
- поле для ввода ФИО;
- поле для ввода email;
- поле для ввода телефона;
- поле выбора даты рождения;
- `Spinner` для выбора пола;
- поле для ввода пароля;
- поле для повторного ввода пароля;
- `CheckBox` для согласия с условиями регистрации;
- кнопка `Зарегистрироваться`.

##### `res/layout/activity_registration.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:background="#FFFFFF">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="24dp">

        <TextView
            android:id="@+id/tvRegistrationTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Регистрация"
            android:textSize="28sp"
            android:textStyle="bold"
            android:gravity="center"
            android:textColor="#000000"
            android:layout_marginBottom="24dp" />

        <EditText
            android:id="@+id/etFio"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="ФИО"
            android:inputType="textPersonName"
            android:layout_marginBottom="12dp" />

        <EditText
            android:id="@+id/etEmail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email"
            android:inputType="textEmailAddress"
            android:layout_marginBottom="12dp" />

        <EditText
            android:id="@+id/etPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Телефон"
            android:inputType="phone"
            android:layout_marginBottom="12dp" />

        <EditText
            android:id="@+id/etBirthDate"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Дата рождения"
            android:focusable="false"
            android:clickable="true"
            android:inputType="none"
            android:layout_marginBottom="12dp" />

        <Spinner
            android:id="@+id/spinnerGender"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="12dp" />

        <EditText
            android:id="@+id/etPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Пароль"
            android:inputType="textPassword"
            android:layout_marginBottom="12dp" />

        <EditText
            android:id="@+id/etRepeatPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Повторите пароль"
            android:inputType="textPassword"
            android:layout_marginBottom="12dp" />

        <CheckBox
            android:id="@+id/cbAgree"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Согласен с условиями регистрации"
            android:layout_marginBottom="20dp" />

        <Button
            android:id="@+id/btnRegister"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Зарегистрироваться" />

    </LinearLayout>

</ScrollView>
```

<div align="center">

<img width="365" height="783" alt="image" src="https://github.com/user-attachments/assets/904891ae-af99-45d4-ad4a-323658d5b29b" />

*Рисунок 5. Экран регистрации пользователя*

</div>

---

#### Задание 4: Реализация DatePicker, Spinner и валидации формы

1. В файле `RegistrationActivity.java` были объявлены переменные для всех элементов формы.
2. Для выбора даты рождения был реализован `DatePickerDialog`.
3. Для выбора пола был настроен `Spinner`.
4. Для кнопки регистрации была реализована валидация всех полей.
5. При ошибке используется метод `setError()` и выводится сообщение `Toast`.
6. При правильном заполнении формы появляется сообщение `Регистрация успешна`.

##### `RegistrationActivity.java`

```java
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
```

<div align="center">

<img width="356" height="777" alt="image" src="https://github.com/user-attachments/assets/6e42661c-8fec-478d-bca4-e563e66c7375" />

*Рисунок 6. Выбор значения в Spinner*

</div>

<div align="center">

<img width="396" height="789" alt="image" src="https://github.com/user-attachments/assets/67ff3a77-311a-41dc-a786-bd952ca23447" />

*Рисунок 7. Ошибки при неправильном заполнении формы*

</div>

<div align="center">

<img width="373" height="789" alt="image" src="https://github.com/user-attachments/assets/e99fa6a7-824a-44fa-bf27-0b18a10eaf42" />

*Рисунок 8. Успешная регистрация пользователя*

</div>

---

#### Задание 5: Проверка AndroidManifest.xml

1. После создания `RegistrationActivity` была проверена регистрация активности в файле `AndroidManifest.xml`.
2. В манифесте присутствует запись для `RegistrationActivity`.
3. Благодаря этому переход с главного экрана на форму регистрации выполняется корректно.

##### Фрагмент файла `AndroidManifest.xml`

```xml
<activity android:name=".RegistrationActivity" />
```

---

### Вывод

В результате выполнения практической работы были изучены механизмы локализации Android-приложений, получены навыки работы со списками `ListView` и `Spinner`, а также освоены различные типы полей ввода.

В приложении был реализован главный экран со списком одногруппников. Список заполняется с помощью массива строк из ресурсов и адаптера `ArrayAdapter`. При нажатии на элемент списка отображается всплывающее сообщение `Toast`.

Также был создан экран регистрации пользователя. В форме регистрации были использованы поля ввода `EditText`, выпадающий список `Spinner`, диалог выбора даты `DatePickerDialog`, флажок `CheckBox` и кнопка регистрации. Для формы была реализована проверка введённых данных: заполнение обязательных полей, корректность email, выбор даты рождения, выбор пола, длина пароля, совпадение паролей и согласие с условиями регистрации.

Таким образом, цель практической работы была достигнута.

---

### Ответы на контрольные вопросы

1. **Как в Android реализуется локализация приложений? Опишите структуру папок для поддержки нескольких языков.**

   Локализация в Android реализуется с помощью файлов ресурсов. Основные строки приложения хранятся в файле `strings.xml`. Для разных языков создаются отдельные папки с языковыми квалификаторами.

   Пример структуры:

   ```text
   res/values/strings.xml
   res/values-en/strings.xml
   ```

   В файле `res/values/strings.xml` хранятся строки по умолчанию, а в `res/values-en/strings.xml` — строки для английской локализации.

---

2. **Для чего используются адаптеры, например ArrayAdapter, при работе со списками ListView и Spinner?**

   Адаптеры используются как связующее звено между источником данных и элементом интерфейса. Например, `ArrayAdapter` получает массив строк и преобразует каждый элемент массива в строку списка.

   Без адаптера `ListView` и `Spinner` не смогут отобразить данные, так как им необходимо указать, какие элементы показывать и каким образом их выводить.

   Пример:

   ```java
   ArrayAdapter<String> adapter = new ArrayAdapter<>(
           this,
           android.R.layout.simple_list_item_1,
           groupmates
   );

   listViewGroupmates.setAdapter(adapter);
   ```

---

3. **Какие атрибуты EditText позволяют ограничить тип вводимых данных? Приведите примеры.**

   Для ограничения типа вводимых данных используется атрибут `android:inputType`.

   Примеры:

   ```xml
   android:inputType="textPersonName"
   android:inputType="textEmailAddress"
   android:inputType="phone"
   android:inputType="textPassword"
   android:inputType="number"
   ```

   Например, для поля email используется:

   ```xml
   <EditText
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="Email"
       android:inputType="textEmailAddress" />
   ```

---

4. **Что такое регулярные выражения? Как с их помощью проверить, что строка является валидным email-адресом?**

   Регулярные выражения — это шаблоны, которые используются для проверки строки на соответствие определённому формату. С их помощью можно проверить email, телефон, пароль и другие данные.

   Пример проверки email с помощью регулярного выражения:

   ```java
   if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
       Toast.makeText(this, "Email корректный", Toast.LENGTH_SHORT).show();
   }
   ```

   В данной работе для проверки email также можно использовать встроенный класс `Patterns`:

   ```java
   Patterns.EMAIL_ADDRESS.matcher(email).matches()
   ```

---

5. **Как программно установить ошибку на поле ввода EditText, чтобы она отображалась пользователю?**

   Для отображения ошибки используется метод `setError()`.

   Пример:

   ```java
   etEmail.setError("Введите корректный email");
   ```

   После вызова этого метода поле ввода подсвечивается, и рядом с ним появляется значок ошибки. Пользователь может нажать на него и увидеть текст ошибки.

---

6. **В чём разница между CheckBox и RadioGroup? В каких случаях используется каждый из них?**

   `CheckBox` используется для независимого выбора одного или нескольких вариантов. Пользователь может поставить или снять галочку.

   `RadioGroup` используется для выбора только одного варианта из группы. Внутри `RadioGroup` размещаются элементы `RadioButton`, и одновременно может быть выбран только один из них.

   Примеры использования:
- `CheckBox` — согласие с условиями регистрации;
- `RadioGroup` — выбор пола, способа оплаты или другого единственного варианта.

---

7. **Как вывести диалоговое окно для выбора даты DatePickerDialog и получить выбранное значение?**

   Для вывода окна выбора даты используется класс `DatePickerDialog`.

   Пример:

   ```java
   Calendar now = Calendar.getInstance();

   DatePickerDialog datePickerDialog = new DatePickerDialog(
           this,
           (view, year, month, dayOfMonth) -> {
               String date = String.format(
                       "%02d.%02d.%04d",
                       dayOfMonth,
                       month + 1,
                       year
               );

               etBirthDate.setText(date);
           },
           now.get(Calendar.YEAR),
           now.get(Calendar.MONTH),
           now.get(Calendar.DAY_OF_MONTH)
   );

   datePickerDialog.show();
   ```

   После выбора даты значение можно записать в `EditText` или другой элемент интерфейса.

---

8. **Для чего используется метод String.matches()? Что он возвращает?**

   Метод `String.matches()` используется для проверки строки на соответствие регулярному выражению.

   Метод возвращает:
- `true`, если строка соответствует шаблону;
- `false`, если строка не соответствует шаблону.

   Пример:

   ```java
   String phone = "+79384082393";

   if (phone.matches("^\\+7\\d{10}$")) {
       Toast.makeText(this, "Телефон введён корректно", Toast.LENGTH_SHORT).show();
   }
   ```
