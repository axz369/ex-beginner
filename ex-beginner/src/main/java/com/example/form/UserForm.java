package com.example.form;

import jakarta.validation.constraints.*;

public class UserForm {
    //名前
    @NotBlank(message = "名前を入力してください")
    private String name;

    //年齢
    @NotNull(message = "年齢を入力してください")
    @Min(value = 0, message = "年齢は0歳以上で入力してください")
    @Max(value = 120, message = "年齢は120歳以下で入力してください")
    private Integer age;

    //コメント
    @NotBlank(message = "コメントを入力してください")
    @Size(max = 300, message = "コメントは300文字以内で入力してください")
    private String comment;



    //getter setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    //toString
    @Override
    public String toString() {
        return "UserReceiveForm{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", comment='" + comment + '\'' +
                '}';
    }
}
