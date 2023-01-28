insert into `carts` (user_id)
values (1),
       (2); # must have a user already


insert into `cart_entries` (quantity, cart_id, product_id)
values (5, 4, 1), # must have a product already
        (12, 5, 1);