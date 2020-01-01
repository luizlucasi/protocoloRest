import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISetor, defaultValue } from 'app/shared/model/setor.model';

export const ACTION_TYPES = {
  FETCH_SETOR_LIST: 'setor/FETCH_SETOR_LIST',
  FETCH_SETOR: 'setor/FETCH_SETOR',
  CREATE_SETOR: 'setor/CREATE_SETOR',
  UPDATE_SETOR: 'setor/UPDATE_SETOR',
  DELETE_SETOR: 'setor/DELETE_SETOR',
  RESET: 'setor/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISetor>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SetorState = Readonly<typeof initialState>;

// Reducer

export default (state: SetorState = initialState, action): SetorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SETOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SETOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SETOR):
    case REQUEST(ACTION_TYPES.UPDATE_SETOR):
    case REQUEST(ACTION_TYPES.DELETE_SETOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SETOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SETOR):
    case FAILURE(ACTION_TYPES.CREATE_SETOR):
    case FAILURE(ACTION_TYPES.UPDATE_SETOR):
    case FAILURE(ACTION_TYPES.DELETE_SETOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SETOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SETOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SETOR):
    case SUCCESS(ACTION_TYPES.UPDATE_SETOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SETOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/setors';

// Actions

export const getEntities: ICrudGetAllAction<ISetor> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SETOR_LIST,
  payload: axios.get<ISetor>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISetor> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SETOR,
    payload: axios.get<ISetor>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISetor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SETOR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISetor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SETOR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISetor> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SETOR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
