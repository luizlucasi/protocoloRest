import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INumeracao, defaultValue } from 'app/shared/model/numeracao.model';

export const ACTION_TYPES = {
  FETCH_NUMERACAO_LIST: 'numeracao/FETCH_NUMERACAO_LIST',
  FETCH_NUMERACAO: 'numeracao/FETCH_NUMERACAO',
  CREATE_NUMERACAO: 'numeracao/CREATE_NUMERACAO',
  UPDATE_NUMERACAO: 'numeracao/UPDATE_NUMERACAO',
  DELETE_NUMERACAO: 'numeracao/DELETE_NUMERACAO',
  RESET: 'numeracao/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INumeracao>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NumeracaoState = Readonly<typeof initialState>;

// Reducer

export default (state: NumeracaoState = initialState, action): NumeracaoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NUMERACAO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NUMERACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NUMERACAO):
    case REQUEST(ACTION_TYPES.UPDATE_NUMERACAO):
    case REQUEST(ACTION_TYPES.DELETE_NUMERACAO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NUMERACAO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NUMERACAO):
    case FAILURE(ACTION_TYPES.CREATE_NUMERACAO):
    case FAILURE(ACTION_TYPES.UPDATE_NUMERACAO):
    case FAILURE(ACTION_TYPES.DELETE_NUMERACAO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUMERACAO_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NUMERACAO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NUMERACAO):
    case SUCCESS(ACTION_TYPES.UPDATE_NUMERACAO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NUMERACAO):
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

const apiUrl = 'api/numeracaos';

// Actions

export const getEntities: ICrudGetAllAction<INumeracao> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NUMERACAO_LIST,
  payload: axios.get<INumeracao>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INumeracao> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NUMERACAO,
    payload: axios.get<INumeracao>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INumeracao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NUMERACAO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INumeracao> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NUMERACAO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INumeracao> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NUMERACAO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
