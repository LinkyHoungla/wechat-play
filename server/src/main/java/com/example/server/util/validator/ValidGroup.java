package com.example.server.util.validator;

import javax.validation.groups.Default;

public interface ValidGroup extends Default {

    interface Crud extends ValidGroup{
        interface Create extends Crud{

        }

        interface Update extends Crud{

        }

        interface Query extends Crud{

        }

        interface Delete extends Crud{

        }
    }

    interface Type extends ValidGroup{
        interface Account extends Crud{

        }

        interface Goods extends Crud{

        }

        interface Order extends Crud{

        }

    }
}
