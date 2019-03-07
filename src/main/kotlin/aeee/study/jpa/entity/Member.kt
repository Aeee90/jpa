package aeee.study.jpa.entity

import aeee.study.jpa.config.STRING_EMPTY
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="member")
class Member {
    @Id
    @Column(name ="id")
    var id: String = STRING_EMPTY
    @Column(name ="user_name")
    var userName: String = STRING_EMPTY
    @Column(name ="age")
    var age: Int = 0
}