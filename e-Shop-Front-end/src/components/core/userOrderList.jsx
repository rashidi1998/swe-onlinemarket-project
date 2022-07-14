import React, {Component} from "react";
import {add, getOrderItems, update} from "../../services/component/orders";
import UserContext from "../../context/userContext";

class UserOrderList extends Component {
    static contextType = UserContext;
    state = {
        items: [],
    };

    async componentDidMount() {
        const {data} = await getOrderItems(this.context.shoppingCartId);
        let items = data.map(item => {
            item.isInBag = true
            return item
        })
        this.setState({items});
    }

    addToCart = async (id) => {
        await update({
            productId: id, shoppingCartId: this.context.shoppingCartId, isAdd: 1,
        });
        let items = [...this.state.items];
        let selectedItem = items.find((i) => i.product.id === id);
        selectedItem.quantity += 1;
        this.setState({items});
        this.context.updateNumber(++this.context.bagItemsQuantity);
    };

    removeFromCart = async (id) => {
        await update({
            productId: id, shoppingCartId: this.context.shoppingCartId, isAdd: 0,
        });
        let items = [...this.state.items];
        let selectedItem = items.find((i) => i.product.id === id);
        selectedItem.quantity -= 1;
        selectedItem.isInBag = selectedItem.quantity !== 0;
        this.setState({items});
        this.context.updateNumber(--this.context.bagItemsQuantity);
        if (this.context.bagItemsQuantity === 0) this.context.updateShoppingCartId(null)
    };

    render() {
        return (<div className="order-page__container">
            <div className="order-page__content">
                <h2 className="table__head">Bag Items</h2>
                <div className="order-table-head__row">
                    <div className="order-table__columns">Name</div>
                    <div className="order-table__columns">Price</div>
                    <div style={{
                        flexDirection: 'row', display: 'flex', justifyContent: 'center', alignSelf: 'center'
                    }}>Quantity
                    </div>
                    <div className="order-table__columns">Total</div>
                </div>

                {this.state.items.map(({product: {name, price, id}, quantity, isInBag}) => isInBag && (
                    <div className="order-table__row" key={id}>
                        <div className="order-table__columns">{name}</div>
                        <div className="order-table__columns">{price}</div>
                        <div style={{
                            flexDirection: 'row', display: 'flex', justifyContent: 'space-between', alignSelf: 'center'
                        }}>
                            <span style={{
                                cursor: 'pointer',
                                backgroundColor: '#607D8B',
                                paddingBottom: 3,
                                paddingTop: 3,
                                paddingLeft: 10,
                                paddingRight: 10,
                                minWidth: 30,
                                borderRadius: 3,
                                color: '#fff',
                                marginRight: 10
                            }} onClick={() => this.removeFromCart(id)}> - </span>
                            <div className="order-table__columns">{quantity}</div>
                            <span style={{
                                cursor: 'pointer',
                                backgroundColor: '#607D8B',
                                paddingBottom: 5,
                                paddingTop: 5,
                                paddingLeft: 10,
                                paddingRight: 10,
                                minWidth: 30,
                                borderRadius: 3,
                                color: '#fff',
                                marginLeft: 10
                            }}
                                  onClick={() => this.addToCart(id)}> + </span>
                        </div>
                        <div className="order-table__columns">
                            {quantity * price}
                        </div>
                    </div>))}
            </div>
        </div>);
    }
}

export default UserOrderList;
